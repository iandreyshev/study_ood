package ru.iandreyshev.adobeKiller.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ru.iandreyshev.adobeKiller.R
import ru.iandreyshev.adobeKiller.presentation.drawing.drawable.IDrawable
import ru.iandreyshev.adobeKiller.presentation.ui.extension.inflate

class ShapesListRVAdapter : RecyclerView.Adapter<ShapeViewRVHolder>() {

    private var mOnItemClickListener: (IDrawable) -> Unit = {}

    var shapes = listOf<IDrawable>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun onItemClick(listener: (IDrawable) -> Unit) {
        mOnItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShapeViewRVHolder =
            ShapeViewRVHolder(parent.context.inflate(R.layout.item_shape, parent))

    override fun onBindViewHolder(holder: ShapeViewRVHolder, position: Int) =
            holder.onClick { mOnItemClickListener(shapes[position]) }

    override fun getItemCount(): Int =
            shapes.size

}
