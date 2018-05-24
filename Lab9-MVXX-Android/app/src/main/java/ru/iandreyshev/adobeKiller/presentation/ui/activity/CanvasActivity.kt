package ru.iandreyshev.adobeKiller.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import ru.iandreyshev.adobeKiller.presentation.ui.adapter.ShapesListRVAdapter
import ru.iandreyshev.adobeKiller.presentation.viewModel.CanvasViewModel
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_canvas.*
import kotlinx.android.synthetic.main.view_shape_info.*
import org.jetbrains.anko.collections.forEachReversedWithIndex
import org.jetbrains.anko.toast
import pl.aprilapps.easyphotopicker.EasyImage
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.ICanvasInteractor
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.hitTest
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.ui.adapter.AndroidCanvasAdapter
import ru.iandreyshev.adobeKiller.presentation.ui.dialog.DialogFactory
import ru.iandreyshev.adobeKiller.presentation.ui.extension.getWindowSize
import ru.iandreyshev.adobeKiller.presentation.ui.extension.visibleIfOrGone
import kotlin.math.pow
import pl.aprilapps.easyphotopicker.DefaultCallback
import ru.iandreyshev.adobeKiller.R
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import java.io.File

class CanvasActivity : BaseActivity<ICanvasInteractor, CanvasViewModel>(
        viewModelClass = CanvasViewModel::class,
        layout = R.layout.activity_canvas) {

    companion object {
        private const val OPEN_PANEL_SPEED = 1f // offset pow
        private const val CHANGE_PANEL_ALPHA_SPEED = 2f // offset pow
    }

    private val mPanelContentOffset by lazy { getWindowSize().heightPixels / 2 }
    private var mCanvasAdapter = AndroidCanvasAdapter()
    private var mTargetShape: IDrawable? = null
    private val mShapesListAdapter: ShapesListRVAdapter by lazy {
        ShapesListRVAdapter().apply {
            onItemClick { interactor.setTargetShape(it.id) }
        }
    }

    override fun onLayoutCreated(savedInstanceState: Bundle?) {
        setSupportActionBar(tbToolbar)

        with(rvShapesList) {
            adapter = mShapesListAdapter
        }

        shapeInfoView.setOnFillColorClick {
            DialogFactory.fillColorDialog(this) { color ->
                actionWithTargetShape { interactor.changeFillColor(it.id, color) }
            }
        }

        shapeInfoView.setOnStrokeColorClick {
            DialogFactory.editStrokeDialog(this) { color, size ->
                actionWithTargetShape { shape ->
                    size?.let { interactor.resizeStroke(shape.id, size) }
                    color?.let { interactor.changeStrokeColor(shape.id, color) }
                }
            }
        }

        tcvCanvas.onTouch = ::handleCanvasTouch
        tcvCanvas.onTargetChanged { newFrame ->
            mTargetShape?.frame?.let { oldFrame ->
                oldFrame.position = newFrame.position
                oldFrame.resize(newFrame.width, newFrame.height)
            }
            //mTargetShape?.let { interactor.updateShape(it) }
        }

        supSlidingPanel.addPanelSlideListener(PanelListener())
    }

    override val onProvideViewModel: CanvasViewModel.() -> Unit = {
        drawables.observe { shapes ->
            val isShapesExists = shapes?.isEmpty() ?: false
            tvEmptyMessage.visibleIfOrGone(isShapesExists)
            tvEmptyListMessage.visibleIfOrGone(isShapesExists)
            mShapesListAdapter.shapes = shapes ?: listOf()
            reDraw()
        }

        targetDrawable.observe { shape ->
            mTargetShape = shape
            shapeInfoView.setShape(mTargetShape)
            tcvCanvas.setTarget(shape?.frame)
        }

        title.observeNotNull {
            supportActionBar?.title = it
        }

        if (isAttachedFirstTime) {
            isAttachedFirstTime = false
            llPanelContent?.background?.alpha = 0
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.act_add -> DialogFactory.createShapeDialog(this, ::insertShape, ::insertImage)
            R.id.act_save -> interactor.save()
            R.id.act_clear -> interactor.refresh()
            R.id.act_delete -> actionWithTargetShape { interactor.deleteShape(it.id) }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, TakePhotoListener())
    }

    private fun insertShape(shapeType: ShapeType) =
            interactor.insert(shapeType)

    private fun insertImage() =
            EasyImage.openCamera(this, 0)

    private fun reDraw() {
        tcvCanvas.onDrawAction { canvas ->
            mCanvasAdapter.canvas = canvas
            mShapesListAdapter.shapes.forEach { it.draw(mCanvasAdapter) }
        }
        tcvCanvas.invalidate()
    }

    private fun actionWithTargetShape(action: (IDrawable) -> Unit) {
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

        mShapesListAdapter.shapes.forEachReversedWithIndex { _, drawable ->
            if (drawable.frame.hitTest(x, y)) {
                interactor.setTargetShape(drawable.id)
                return
            }
        }

        interactor.setTargetShape(null)
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

    private inner class TakePhotoListener : DefaultCallback() {
        override fun onImagePicked(imageFile: File?, source: EasyImage.ImageSource?, type: Int) {
            if (imageFile != null) {
                interactor.insert(imageFile)
            }
        }

        override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) {
        }

        override fun onCanceled(source: EasyImage.ImageSource?, type: Int) {
        }
    }
}
