package ru.iandreyshev.adobekiller.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_select_color.view.*
import ru.iandreyshev.adobekiller.model.canvas.Color
import ru.iandreyshev.adobekiller.ui.extension.fill

class ColorViewRVHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun initColor(color: Color) = itemView.tcvCanvas.fill(color)
}
