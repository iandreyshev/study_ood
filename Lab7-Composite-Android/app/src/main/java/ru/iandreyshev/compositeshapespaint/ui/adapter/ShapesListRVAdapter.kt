package ru.iandreyshev.compositeshapespaint.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.ui.extension.inflate

class ShapesListRVAdapter(
        private val onClickShape: (IShape) -> Unit
) : RecyclerView.Adapter<ShapeViewRVHolder>() {

    var shapes = listOf<IShape>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShapeViewRVHolder =
            ShapeViewRVHolder(parent.context.inflate(R.layout.item_select_shape))

    override fun onBindViewHolder(holder: ShapeViewRVHolder, position: Int) {
        val item = shapes[position]
        holder.title.text = item.name
        holder.itemView.setOnClickListener { onClickShape(item) }
    }

    override fun getItemCount(): Int = shapes.size
}
