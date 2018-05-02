package ru.iandreyshev.compositeshapespaint.ui.dialog

import android.content.Context
import android.view.View
import com.xw.repo.BubbleSeekBar
import kotlinx.android.synthetic.main.dialog_add_shape.view.*
import kotlinx.android.synthetic.main.dialog_edit_stroke.view.*
import kotlinx.android.synthetic.main.dialog_select_color.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.ui.adapter.ColorsListRVAdapter
import ru.iandreyshev.compositeshapespaint.ui.extension.inflate
import ru.iandreyshev.compositeshapespaint.ui.interfaces.ISeekBarEmptyListener

object DialogFactory {

    fun addShapeDialog(context: Context, onSelect: (String) -> Unit) {
        val view = context.inflate(R.layout.dialog_add_shape)
        val dialog = context.alert {
            title = context.getString(R.string.dialog_title_add_shape)
            customView = view
            cancelButton { }
        }.show()

        fun setOnClickListener(view: View, shape: String) {
            view.setOnClickListener {
                onSelect(shape)
                dialog.dismiss()
            }
        }

        view.apply {
            setOnClickListener(clRect, "rect")
            setOnClickListener(clCircle, "circle")
            setOnClickListener(clTriangle, "triangle")
            setOnClickListener(clPolygon, "polygon")
            setOnClickListener(clImage, "image")
        }
    }

    fun fillColorDialog(context: Context, onClick: (Color) -> Unit) {
        val adapter = ColorsListRVAdapter()
        val dialog = context.alert {
            title = context.getString(R.string.dialog_title_change_fill_color)
            context.inflate(R.layout.dialog_select_color).apply {
                customView = this
                rvColorsList.adapter = adapter
            }
            cancelButton {  }
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
            cancelButton {  }
        }.show()

        adapter.onItemClick = { color ->
            onClick.invoke(color, strokeSize)
            dialog.dismiss()
        }
    }
}
