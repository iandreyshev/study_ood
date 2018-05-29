package ru.iandreyshev.adobeKiller.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import ru.iandreyshev.adobeKiller.presentation.viewModel.CanvasViewModel
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_canvas.*
import kotlinx.android.synthetic.main.view_shape_info.*
import org.jetbrains.anko.collections.forEachReversedWithIndex
import org.jetbrains.anko.toast
import pl.aprilapps.easyphotopicker.EasyImage
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.ICanvasInteractor
import ru.iandreyshev.adobeKiller.presentation.ui.adapter.AndroidCanvasAdapter
import ru.iandreyshev.adobeKiller.presentation.ui.dialog.DialogFactory
import ru.iandreyshev.adobeKiller.presentation.ui.extension.visibleIfOrGone
import pl.aprilapps.easyphotopicker.DefaultCallback
import ru.iandreyshev.adobeKiller.R
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.ITargetCanvasObject
import java.io.File

class CanvasActivity : BaseActivity<ICanvasInteractor, CanvasViewModel>(
        viewModelClass = CanvasViewModel::class,
        layout = R.layout.activity_canvas) {

    private var mObjects: List<IDrawable>? = null
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
            mTarget?.frame?.resize(newFrame.width, newFrame.height)
            mTarget?.frame?.position?.x = newFrame.position.x
            mTarget?.frame?.position?.y = newFrame.position.y
        }
        tcvCanvas.onTouchFinish { isTargetFrameChanged ->
            if (isTargetFrameChanged) {
                mTarget?.applyChanges()
            }
        }
    }

    override val onProvideViewModel: CanvasViewModel.() -> Unit = {
        objects.observe { drawables ->
            mObjects = drawables
            val isShapesExists = drawables?.isEmpty() ?: false
            tvEmptyMessage.visibleIfOrGone(isShapesExists)
            drawObjects()
        }

        target.observe { target ->
            mTarget = target
            shapeInfoView.style = mTarget?.style
            tcvCanvas.setTarget(target?.frame)
        }

        title.observeNotNull {
            supportActionBar?.title = it
        }

        invalidating.observe {
            tcvCanvas.invalidate()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.act_add -> DialogFactory.createShapeDialog(this, ::insertShape, ::insertImage)
            R.id.act_undo -> interactor.undo()
            R.id.act_redo -> interactor.redo()
            R.id.act_save -> interactor.save()
            R.id.act_clear -> interactor.refresh()
            R.id.act_delete -> interactor.delete()
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

    private fun drawObjects() {
        tcvCanvas.onDrawAction { canvas ->
            mCanvasAdapter.canvas = canvas
            mObjects?.forEach { it.draw(mCanvasAdapter) }
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

        mObjects?.forEachReversedWithIndex { _, canvasObject ->
            if (canvasObject.hitTest(x, y)) {
                canvasObject.onClick()
                return
            }
        }

        interactor.setTarget(null)
    }

    private inner class TakePhotoListener : DefaultCallback() {
        override fun onImagePicked(imageFile: File?, source: EasyImage.ImageSource?, type: Int) {
            if (imageFile != null) {
                interactor.insert(imageFile)
            }
        }

        override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) = Unit
        override fun onCanceled(source: EasyImage.ImageSource?, type: Int) = Unit
    }
}
