package ru.iandreyshev.adobeKiller.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_select_color.view.*
import ru.iandreyshev.adobeKiller.presentation.drawing.canvas.Color
import ru.iandreyshev.adobeKiller.presentation.ui.extension.fill

class ColorViewRVHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun initColor(color: Color) = itemView.tcvCanvas.fill(color)

}
