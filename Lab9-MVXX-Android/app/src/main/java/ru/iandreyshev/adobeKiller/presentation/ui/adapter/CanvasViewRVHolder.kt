package ru.iandreyshev.adobeKiller.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_menu_canvas.view.*

class CanvasViewRVHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun setTitle(name: String) {
        itemView.tvTitle.text = name
    }

    fun setOnClickListener(onClick: () -> Unit) {
        itemView.setOnClickListener { onClick() }
    }

    fun setOnLongClickListener(onClick: () -> Unit) {
        itemView.setOnLongClickListener clickListener@{
            onClick()
            return@clickListener true
        }
    }

}
