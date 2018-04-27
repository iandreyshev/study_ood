package ru.iandreyshev.compositeshapespaint.ui.dialog

import android.content.Context
import android.support.annotation.StringRes
import kotlinx.android.synthetic.main.dialog_resize.view.*
import kotlinx.android.synthetic.main.dialog_select_color.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.ui.adapter.ColorsListRVAdapter
import ru.iandreyshev.compositeshapespaint.ui.extension.inflate

object DialogFactory {
    fun shapeSizeDialog(context: Context, onSubmit: (Int) -> Unit) =
            showResizeDialog(context, onSubmit, R.string.act_resize)

    fun strokeSizeDialog(context: Context, onSubmit: (Int) -> Unit) =
            showResizeDialog(context, onSubmit, R.string.act_resize_stroke)

    fun fillColorDialog(context: Context, onClick: (Color) -> Unit) =
            showColorDialog(context, onClick, R.string.act_fill_color)

    fun strokeColorDialog(context: Context, onClick: (Color) -> Unit) =
            showColorDialog(context, onClick, R.string.act_stroke_color)

    private fun showColorDialog(
            context: Context,
            onClick: (Color) -> Unit,
            @StringRes titleRes: Int) = with(context) {

        val adapter = ColorsListRVAdapter()
        val dialog = alert {
            title = getString(titleRes)
            inflate(R.layout.dialog_select_color).apply {
                customView = this
                rvColorsList.adapter = adapter
            }
            okButton { }
        }.show()

        adapter.onItemClick = {
            onClick(it)
            dialog.dismiss()
        }
    }

    private fun showResizeDialog(
            context: Context,
            onSubmit: (Int) -> Unit,
            @StringRes titleRes: Int) = with(context) {

        alert {
            title = getString(titleRes)
            val view = inflate(R.layout.dialog_resize)
            customView = view
            okButton {
                onSubmit(view.sbSizeValue.progress)
            }
        }.show()
    }
}
