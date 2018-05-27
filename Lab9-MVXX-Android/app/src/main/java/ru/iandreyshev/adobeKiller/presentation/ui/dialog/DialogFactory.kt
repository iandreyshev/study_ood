package ru.iandreyshev.adobeKiller.presentation.ui.dialog

import android.content.Context
import android.view.View
import com.xw.repo.BubbleSeekBar
import kotlinx.android.synthetic.main.dialog_create_shape.view.*
import kotlinx.android.synthetic.main.dialog_create_canvas.view.*
import kotlinx.android.synthetic.main.dialog_edit_stroke.view.*
import kotlinx.android.synthetic.main.dialog_select_color.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton
import ru.iandreyshev.adobeKiller.R
import ru.iandreyshev.adobeKiller.domain.model.ShapeType
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.ui.adapter.ColorsListRVAdapter
import ru.iandreyshev.adobeKiller.presentation.ui.extension.inflate
import ru.iandreyshev.adobeKiller.presentation.ui.interfaces.ISeekBarEmptyListener

object DialogFactory {

    fun createShapeDialog(
            context: Context,
            onShape: (ShapeType) -> Unit,
            onImage: () -> Unit
    ) {
        val view = context.inflate(R.layout.dialog_create_shape)
        val dialog = context.alert {
            title = context.getString(R.string.dialog_title_add_shape)
            customView = view
            cancelButton { }
        }.show()

        fun View.doAndDismiss(action: () -> Unit) =
                setOnClickListener {
                    action()
                    dialog.dismiss()
                }

        view.apply {
            clRect.doAndDismiss { onShape(ShapeType.Rect) }
            clCircle.doAndDismiss { onShape(ShapeType.Ellipse) }
            clTriangle.doAndDismiss { onShape(ShapeType.Triangle) }
            clImage.doAndDismiss { onImage() }
        }
    }

    fun createCanvasDialog(context: Context, onSubmit: (String) -> Unit) {
        context.alert {
            title = "Create canvas"
            val view = context.inflate(R.layout.dialog_create_canvas).apply {
                customView = this
            }
            okButton { onSubmit(view.etCanvasName.text.toString()) }
            cancelButton { }
        }.show()
    }

    fun fillColorDialog(context: Context, onClick: (Color) -> Unit) {
        val adapter = ColorsListRVAdapter()
        val dialog = context.alert {
            title = context.getString(R.string.dialog_title_change_fill_color)
            context.inflate(R.layout.dialog_select_color).apply {
                customView = this
                rvColorsList.adapter = adapter
            }
            cancelButton { }
        }.show()

        adapter.onItemClick = {
            onClick.invoke(it)
            dialog.dismiss()
        }
    }

    fun editStrokeDialog(context: Context, onClick: (Color?, Int?) -> Unit) {
        var strokeSize: Int? = null
        val adapter = ColorsListRVAdapter()
        val dialog = context.alert {
            title = context.getString(R.string.dialog_title_edit_stroke)
            context.inflate(R.layout.dialog_edit_stroke).apply {
                customView = this
                rvStrokeColorsList.adapter = adapter
                sbStrokeSize.onProgressChangedListener = object : ISeekBarEmptyListener {
                    override fun onProgressChanged(bubbleSeekBar: BubbleSeekBar?, progress: Int, progressFloat: Float, fromUser: Boolean) {
                        if (fromUser) {
                            strokeSize = progress
                        }
                    }
                }
            }
            okButton {
                onClick.invoke(null, strokeSize)
            }
            cancelButton { }
        }.show()

        adapter.onItemClick = { color ->
            onClick.invoke(color, strokeSize)
            dialog.dismiss()
        }
    }

}
