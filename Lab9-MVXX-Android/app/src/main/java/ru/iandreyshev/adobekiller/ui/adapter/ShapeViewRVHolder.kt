package ru.iandreyshev.adobekiller.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.item_shape.view.*

class ShapeViewRVHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView by lazy { view.tvTitle }
}
