package ru.iandreyshev.compositeshapespaint.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.lay_shape_select_list_item.view.*

class ShapeViewRVHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView by lazy { view.tvTitle }
}
