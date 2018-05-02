package ru.iandreyshev.compositeshapespaint.ui.activity

import android.os.Bundle
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.ui.adapter.ShapesListRVAdapter
import ru.iandreyshev.compositeshapespaint.ui.viewModel.main.MainViewModel
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_shape_info.*
import org.jetbrains.anko.collections.forEachReversedWithIndex
import org.jetbrains.anko.toast
import ru.iandreyshev.compositeshapespaint.interactor.interfaces.IMainInteractor
import ru.iandreyshev.compositeshapespaint.ui.ActionError
import ru.iandreyshev.compositeshapespaint.factory.CleanArchitectureFactory
import ru.iandreyshev.compositeshapespaint.model.shape.frame.hitTest
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.ui.adapter.AndroidCanvasAdapter
import ru.iandreyshev.compositeshapespaint.ui.dialog.DialogFactory
import ru.iandreyshev.compositeshapespaint.ui.extension.getWindowSize
import ru.iandreyshev.compositeshapespaint.ui.extension.visibleIfOrGone
import ru.iandreyshev.compositeshapespaint.ui.viewModel.main.IMainActivityStateContext
import ru.iandreyshev.compositeshapespaint.ui.viewModel.main.MainActivityState
import kotlin.math.pow

class MainActivity : InteractorActivity<IMainInteractor, MainViewModel>(
        layout = R.layout.activity_main,
        viewModelClass = MainViewModel::class,
        viewModelFactory = CleanArchitectureFactory) {

    companion object {
        private const val OPEN_PANEL_SPEED = 1f // offset pow
        private const val CHANGE_PANEL_ALPHA_SPEED = 2f // offset pow
    }

    private var mCurrentState: MainActivityState? = null
    private val mPanelContentOffset by lazy { getWindowSize().heightPixels / 2 }
    private var mCanvasAdapter = AndroidCanvasAdapter()
    private var mTargetShape: IShape? = null
    private val mShapesListAdapter: ShapesListRVAdapter by lazy {
        ShapesListRVAdapter({ mCurrentState?.onShapeSelected(it) })
    }

    override fun onLayoutCreated(savedInstanceState: Bundle?) {
        setSupportActionBar(tbToolbar)

        with(rvShapesList) {
            adapter = mShapesListAdapter
        }

        shapeInfoView.setOnFillColorClick {
            DialogFactory.fillColorDialog(this) { color ->
                actionWithTargetShape { interactor.changeFillColor(it, color) }
            }
        }

        shapeInfoView.setOnStrokeColorClick {
            DialogFactory.editStrokeDialog(this) { color, size ->
                actionWithTargetShape { shape ->
                    size?.let { interactor.resizeStroke(shape, size) }
                    color?.let { interactor.changeStrokeColor(shape, color) }
                }
            }
        }

        tcvCanvas.onTouch = ::handleCanvasTouch
        tcvCanvas.onTargetChanged { newFrame ->
            mTargetShape?.frame?.apply {
                position = newFrame.position
                resize(newFrame.width, newFrame.height)
            }

            mTargetShape?.let { interactor.updateShape(it) }
        }

        supSlidingPanel.addPanelSlideListener(PanelListener())
    }

    override val onProvideViewModel: MainViewModel.() -> Unit = {
        shapes.observe { shapes ->
            val isShapesExists = shapes?.isEmpty() ?: false
            tvEmptyMessage.visibleIfOrGone(isShapesExists)
            tvEmptyListMessage.visibleIfOrGone(isShapesExists)
            mShapesListAdapter.shapes = shapes ?: listOf()
            reDraw()
        }

        targetShape.observe { shape ->
            mTargetShape = shape
            shapeInfoView.setShape(mTargetShape)
            tcvCanvas.isEnabled = shape?.frame != null
            shape?.frame?.apply { tcvCanvas.setTarget(position, width, height) }
        }

        state.observeNotNull { state ->
            mCurrentState = state
            mCurrentState?.context = StateContext()
            mCurrentState?.actionCallback?.let {
                startSupportActionMode(it)
            }
        }

        if (isAttachedFirstTime) {
            isAttachedFirstTime = false
            llPanelContent?.background?.alpha = 0
        }

        error.observe { error ->
            handleActionError(error)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.act_add -> DialogFactory.addShapeDialog(this, interactor::addShape)
            R.id.act_grouping -> interactor.beginGrouping()
            R.id.act_refresh -> interactor.refresh()
            R.id.act_delete -> actionWithTargetShape { interactor.deleteShape(it) }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun reDraw() {
        tcvCanvas.onDrawAction { canvas ->
            mCanvasAdapter.canvas = canvas
            mShapesListAdapter.shapes.forEach { it.draw(mCanvasAdapter) }
        }
        tcvCanvas.invalidate()
    }

    private fun handleActionError(error: ActionError?) {
        when (error) {
            ActionError.SHAPE_NOT_SELECTED -> toast(R.string.action_error_null_shape)
            ActionError.UNDEFINED_ERR -> toast(R.string.action_error_undefined)
        }
    }

    private fun actionWithTargetShape(action: (IShape) -> Unit) {
        mTargetShape.apply {
            when (this) {
                null -> toast(R.string.shape_not_selected)
                else -> action(this)
            }
        }
    }

    private fun handleCanvasTouch(x: Float, y: Float) {
        if (tcvCanvas.hitTest(x, y)) {
            return
        }

        mShapesListAdapter.shapes.forEachReversedWithIndex { _, shape ->
            if (shape.frame.hitTest(x, y)) {
                mCurrentState?.onShapeSelected(shape)
                return
            }
        }

        mCurrentState?.onClickOutsideShape()
    }

    private inner class StateContext : IMainActivityStateContext {
        override val interactor: IMainInteractor
            get() = this@MainActivity.interactor
        override var targetShape: IShape? = null
            get() = this@MainActivity.mTargetShape
    }

    private inner class PanelListener : SlidingUpPanelLayout.PanelSlideListener {
        override fun onPanelSlide(panel: View?, slideOffset: Float) {
            ibPanelButton.rotation = 180 * slideOffset
            val translation = slideOffset.pow(OPEN_PANEL_SPEED) * mPanelContentOffset
            tvPanelTitle.translationY = mPanelContentOffset - translation
            rvShapesList.translationY = mPanelContentOffset - translation
            tvEmptyListMessage.translationY = mPanelContentOffset - translation

            val alpha = slideOffset.pow(CHANGE_PANEL_ALPHA_SPEED)
            tvPanelTitle.alpha = alpha
            rvShapesList.alpha = alpha
            llPanelContent?.background?.alpha = (255 * alpha).toInt()
        }

        override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState?) {
        }
    }
}
