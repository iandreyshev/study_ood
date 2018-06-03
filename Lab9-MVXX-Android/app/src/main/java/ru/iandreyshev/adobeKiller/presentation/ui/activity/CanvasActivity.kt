package ru.iandreyshev.adobeKiller.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_canvas.*
import kotlinx.android.synthetic.main.view_shape_info.*
import org.jetbrains.anko.collections.forEachReversedWithIndex
import org.jetbrains.anko.toast
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import ru.iandreyshev.adobeKiller.R
import ru.iandreyshev.adobeKiller.domain.controller.interfaces.ICanvasViewController
import ru.iandreyshev.adobeKiller.domain.canvasEngine.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.copyFrom
import ru.iandreyshev.adobeKiller.presentation.ui.adapter.AndroidCanvasAdapter
import ru.iandreyshev.adobeKiller.presentation.ui.dialog.DialogFactory
import ru.iandreyshev.adobeKiller.presentation.ui.extension.visibleIfOrGone
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.ITargetCanvasObject
import ru.iandreyshev.adobeKiller.presentation.viewModel.CanvasViewModel
import java.io.File

class CanvasActivity : BaseActivity<ICanvasViewController, CanvasViewModel>(
        viewModelClass = CanvasViewModel::class,
        layout = R.layout.activity_canvas) {

    private var mObjects: List<IDrawable> = listOf()
    private var mTarget: ITargetCanvasObject? = null
    private var mCanvasAdapter = AndroidCanvasAdapter()

    override fun onLayoutCreated(savedInstanceState: Bundle?) {
        setSupportActionBar(tbToolbar)

        shapeInfoView.setOnFillColorClick {
            DialogFactory.fillColorDialog(this) { color ->
                actionWithTarget {
                    it.style.fillColor = color
                }
            }
        }

        shapeInfoView.setOnStrokeColorClick {
            DialogFactory.editStrokeDialog(this) { color, size ->
                actionWithTarget { canvasObject ->
                    size?.let { canvasObject.style.strokeSize = size.toFloat() }
                    color?.let { canvasObject.style.strokeColor = color }
                }
            }
        }

        tcvCanvas.onTouchStart(::handleCanvasTouch)
        tcvCanvas.onTargetFrameChanged { newFrame ->
            mTarget?.frame?.let {
                it copyFrom newFrame
            }
            drawObjects()
        }
        tcvCanvas.onTouchFinish { isTargetFrameChanged ->
            if (isTargetFrameChanged) actionWithTarget { }
        }
    }

    override val onProvideViewModel: CanvasViewModel.() -> Unit = {
        objects.observe { drawables ->
            mObjects = drawables ?: listOf()
            val isShapesExists = drawables?.isEmpty() ?: false
            tvEmptyMessage.visibleIfOrGone(isShapesExists)
            drawObjects()
        }

        target.observe { target ->
            mTarget = target
            shapeInfoView.style = mTarget?.style
            tcvCanvas.setTarget(mTarget?.frame)
        }

        title.observeNotNull {
            supportActionBar?.title = it
        }

        invalidating.observe {
            drawObjects()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.act_add -> DialogFactory.createShapeDialog(this, ::insertShape, ::insertImage)
            R.id.act_undo -> controller.undo()
            R.id.act_redo -> controller.redo()
            R.id.act_save -> controller.save()
            R.id.act_clear -> controller.refresh()
            R.id.act_delete -> mTarget?.delete()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, TakePhotoListener())
    }

    private fun insertShape(shapeType: ShapeType) =
            controller.insert(shapeType)

    private fun insertImage() =
            EasyImage.openCamera(this, 0)

    private fun drawObjects() {
        tcvCanvas.onDrawAction { canvas ->
            mCanvasAdapter.canvas = canvas
            mObjects.forEach { it.draw(mCanvasAdapter) }
        }
        tcvCanvas.invalidate()
    }

    private fun actionWithTarget(action: (ITargetCanvasObject) -> Unit) {
        mTarget.apply {
            when (this) {
                null -> toast(R.string.shape_not_selected)
                else -> {
                    action(this)
                    this.applyChanges()
                }
            }
        }
    }

    private fun handleCanvasTouch(x: Float, y: Float) {
        if (tcvCanvas.hitTest(x, y)) {
            return
        }

        mObjects.forEachReversedWithIndex { _, canvasObject ->
            if (canvasObject.hitTest(x, y)) {
                canvasObject.onClick()
                return
            }
        }

        controller.resetTarget()
    }

    private inner class TakePhotoListener : DefaultCallback() {
        override fun onImagePicked(imageFile: File?, source: EasyImage.ImageSource?, type: Int) {
            if (imageFile != null) {
                controller.insert(imageFile)
            }
        }

        override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) = Unit
        override fun onCanceled(source: EasyImage.ImageSource?, type: Int) = Unit
    }
}
