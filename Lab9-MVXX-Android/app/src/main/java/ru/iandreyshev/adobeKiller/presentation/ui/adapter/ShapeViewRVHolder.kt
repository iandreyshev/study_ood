package ru.iandreyshev.adobeKiller.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.item_shape.view.*

class ShapeViewRVHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val title: TextView by lazy { view.tvTitle }

    fun onClick(action: () -> Unit) {
        itemView.setOnClickListener {
            action()
        }
    }

}
