package ru.iandreyshev.adobeKiller.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_menu_canvas.view.*

class CanvasViewRVHolder(view: View) : RecyclerView.ViewHolder(view) {

    operator fun invoke(name: String, onClick: () -> Unit) {
        itemView.tvTitle.text = name
        itemView.setOnClickListener { onClick() }
    }

}
