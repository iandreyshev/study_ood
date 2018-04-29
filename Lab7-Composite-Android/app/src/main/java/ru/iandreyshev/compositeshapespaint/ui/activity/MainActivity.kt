package ru.iandreyshev.compositeshapespaint.ui.activity

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.ui.adapter.ShapesListRVAdapter
import ru.iandreyshev.compositeshapespaint.ui.viewModel.main.MainViewModel
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_shape_info.*
import org.jetbrains.anko.toast
import ru.iandreyshev.compositeshapespaint.interactor.interfaces.IMainInteractor
import ru.iandreyshev.compositeshapespaint.ui.ActionError
import ru.iandreyshev.compositeshapespaint.factory.CleanArchitectureFactory
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.ui.adapter.AndroidCanvasAdapter
import ru.iandreyshev.compositeshapespaint.ui.dialog.DialogFactory
import ru.iandreyshev.compositeshapespaint.ui.extension.visibleIfOrGone
import ru.iandreyshev.compositeshapespaint.ui.viewModel.main.IMainActivityStateContext

class MainActivity : InteractorActivity<IMainInteractor, MainViewModel>(
        layout = R.layout.activity_main,
        viewModelClass = MainViewModel::class,
        viewModelFactory = CleanArchitectureFactory) {

    private var mCanvasAdapter = AndroidCanvasAdapter()
    private var mTargetShape: IShape? = null
    private val mShapesListAdapter: ShapesListRVAdapter by lazy {
        ShapesListRVAdapter({ interactor.setTargetShape(it) })
    }

    override fun onLayoutCreated(savedInstanceState: Bundle?) {
        setSupportActionBar(tbToolbar)

        with(rvShapesList) {
            adapter = mShapesListAdapter
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
        }

        shapeInfoView.setOnFillColorClick {
            DialogFactory.fillColorDialog(this) { color ->
                actionWithTargetShape { interactor.changeFillColor(it, color) }
            }
        }

        shapeInfoView.setOnStrokeColorClick {
            DialogFactory.editStrokeDialog(this) { color ->
                actionWithTargetShape { interactor.changeStrokeColor(it, color) }
            }
        }

        tcvCanvas.onChangeTarget { newFrame ->
            mTargetShape?.frame?.apply {
                position = newFrame.position
                resize(newFrame.width, newFrame.height)
            }

            mTargetShape?.let { interactor.updateShape(it) }
        }
    }

    override val onProvideViewModel: MainViewModel.() -> Unit = {
        shapes.observe { shapes ->
            tvEmptyMessage.visibleIfOrGone(shapes?.isEmpty() ?: false)
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
            state.context = StateContext()
            tcvCanvas.onMove = state::handleCanvasTouchMove
            state.actionCallback?.let {
                startSupportActionMode(it)
            }
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
            R.id.act_show_info -> actionWithTargetShape { interactor.showShapeInfo(it) }
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

    private inner class StateContext : IMainActivityStateContext {
        override val interactor: IMainInteractor
            get() = this@MainActivity.interactor
        override var targetShape: IShape? = null
            get() = this@MainActivity.mTargetShape
    }
}
