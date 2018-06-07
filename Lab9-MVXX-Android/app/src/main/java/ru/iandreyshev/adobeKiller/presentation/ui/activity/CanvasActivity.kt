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
import ru.iandreyshev.adobeKiller.app.AdobeKillerApplication
import ru.iandreyshev.adobeKiller.interactor.ICanvasInteractor
import ru.iandreyshev.adobeKiller.presentation.drawing.extension.copyFrom
import ru.iandreyshev.adobeKiller.presentation.ui.adapter.AndroidCanvasAdapter
import ru.iandreyshev.adobeKiller.presentation.ui.dialog.DialogFactory
import ru.iandreyshev.adobeKiller.presentation.ui.extension.visibleIfOrGone
import ru.iandreyshev.adobeKiller.presentation.ui.targetFrame.ITargetCanvasObject
import ru.iandreyshev.adobeKiller.presentation.ui.view.CanvasTargetView
import ru.iandreyshev.adobeKiller.presentation.viewModel.ICanvasViewModel
import ru.iandreyshev.geometry.frame.IConstFrame
import java.io.File

class CanvasActivity : BaseAppCompatActivity(R.layout.activity_canvas) {

    private lateinit var mViewModel: ICanvasViewModel
    private lateinit var mInteractor: ICanvasInteractor
    private var mTarget: ITargetCanvasObject? = null
    private var mCanvasAdapter = AndroidCanvasAdapter()

    override fun onLayoutCreated(savedInstanceState: Bundle?) {
        initDependences()
        initViewElements()
        initViewModelObservers()

        mInteractor
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.act_add -> DialogFactory.createShapeDialog(
                    this,
                    { shapeType -> mInteractor.insert(shapeType) },
                    { EasyImage.openCamera(this, 0) })
            R.id.act_undo -> mInteractor.undo()
            R.id.act_redo -> mInteractor.redo()
            R.id.act_save -> mInteractor.save()
            R.id.act_clear -> mInteractor.refresh()
            R.id.act_delete -> mTarget?.delete()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, TakePhotoListener())
    }

    private fun initDependences() {
        val dependences = AdobeKillerApplication.dependencyFactory[this]
        mViewModel = dependences.viewModel
        mInteractor = dependences.interactor
    }

    private fun initViewModelObservers() = with(mViewModel) {
        objects.observe { drawables ->
            val isShapesExists = drawables?.isEmpty() ?: false
            tvEmptyMessage.visibleIfOrGone(isShapesExists)

            tcvCanvas.onDrawAction { canvas ->
                mCanvasAdapter.canvas = canvas
                mViewModel.objects.value?.forEach { it.draw(mCanvasAdapter) }
            }

            tcvCanvas.invalidate()
        }

        target.observe { target ->
            mTarget = target
            shapeInfoView.style = mTarget?.style
            tcvCanvas.setTarget(mTarget?.frame)
        }
    }

    private fun initViewElements() {
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

        tcvCanvas.setListener(TargetFrameListener())
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

    private inner class TakePhotoListener : DefaultCallback() {
        override fun onImagePicked(imageFile: File?, source: EasyImage.ImageSource?, type: Int) {
            if (imageFile != null) {
                mInteractor.insertPhoto(imageFile)
            }
        }

        override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) = Unit
        override fun onCanceled(source: EasyImage.ImageSource?, type: Int) = Unit
    }

    private inner class TargetFrameListener : CanvasTargetView.IListener {

        override fun onTouchStart(x: Float, y: Float) {
            if (tcvCanvas.hitTest(x, y)) {
                return
            }

            mViewModel.objects.value?.forEachReversedWithIndex { _, canvasObject ->
                if (canvasObject.hitTest(x, y)) {
                    canvasObject.onSelect()
                    return
                }
            }
        }

        override fun onFrameChanged(frame: IConstFrame) {
            mTarget?.frame?.let { targetFrame ->
                targetFrame copyFrom frame
            }
        }

        override fun onTouchFinish(isFrameChanged: Boolean, frame: IConstFrame?) {
            mTarget?.frame?.let { targetFrame ->
                frame?.let { newFrame ->
                    targetFrame copyFrom newFrame
                }
            }
            if (isFrameChanged) actionWithTarget { }
        }

    }
}
