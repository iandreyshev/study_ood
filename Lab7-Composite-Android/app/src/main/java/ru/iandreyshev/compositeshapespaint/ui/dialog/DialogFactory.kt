package ru.iandreyshev.compositeshapespaint.ui.dialog

import android.content.Context
import kotlinx.android.synthetic.main.dialog_select_color.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.ui.adapter.ColorsListRVAdapter
import ru.iandreyshev.compositeshapespaint.ui.extension.inflate

object DialogFactory {

    fun addShapeDialog(context: Context, onSelect: (String) -> Unit) {

    }

    fun fillColorDialog(context: Context, onClick: (Color) -> Unit) {
        val adapter = ColorsListRVAdapter()
        val dialog = context.alert {
            title = context.getString(R.string.dialog_title_change_fill_color)
            context.inflate(R.layout.dialog_select_color).apply {
                customView = this
                rvColorsList.adapter = adapter
            }
            okButton { }
        }.show()

        adapter.onItemClick = {
            onClick.invoke(it)
            dialog.dismiss()
        }
    }

    fun editStrokeDialog(context: Context, onClick: (Color) -> Unit) {
        val adapter = ColorsListRVAdapter()
        val dialog = context.alert {
            title = context.getString(R.string.dialog_title_edit_stroke)
            context.inflate(R.layout.dialog_edit_stroke).apply {
                customView = this
                rvColorsList.adapter = adapter
            }
            okButton { }
        }.show()

        adapter.onItemClick = {
            onClick.invoke(it)
            dialog.dismiss()
        }
    }
}
