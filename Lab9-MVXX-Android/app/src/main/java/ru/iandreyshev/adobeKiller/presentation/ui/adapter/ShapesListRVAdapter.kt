package ru.iandreyshev.adobeKiller.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ru.iandreyshev.adobeKiller.R
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.presentation.ui.extension.inflate

class ShapesListRVAdapter : RecyclerView.Adapter<ShapeViewRVHolder>() {

    private var mOnItemClickListener: (CanvasObject) -> Unit = {}

    var data = listOf<CanvasObject>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun onItemClick(listener: (CanvasObject) -> Unit) {
        mOnItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShapeViewRVHolder =
            ShapeViewRVHolder(parent.context.inflate(R.layout.item_shape, parent))

    override fun onBindViewHolder(holder: ShapeViewRVHolder, position: Int) =
            holder.onClick { mOnItemClickListener(data[position]) }

    override fun getItemCount(): Int =
            data.size

}
