package ru.iandreyshev.adobeKiller.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import ru.iandreyshev.adobeKiller.R
import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.presentation.ui.extension.inflate

class CanvasesListRVAdapter : RecyclerView.Adapter<CanvasViewRVHolder>() {

    private var mCanvases: List<CanvasData> = listOf()
    private var mOnItemClickListener: (CanvasData) -> Unit = {}

    fun setCanvases(canvases: List<CanvasData>) {
        mCanvases = canvases
        notifyDataSetChanged()
    }

    fun onItemClick(listener: (CanvasData) -> Unit) {
        mOnItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CanvasViewRVHolder =
            CanvasViewRVHolder(parent.context.inflate(R.layout.item_menu_canvas, parent))

    override fun onBindViewHolder(holder: CanvasViewRVHolder, position: Int) =
            holder(mCanvases[position].name) {
                mOnItemClickListener(mCanvases[position])
            }

    override fun getItemCount(): Int =
            mCanvases.size

}
