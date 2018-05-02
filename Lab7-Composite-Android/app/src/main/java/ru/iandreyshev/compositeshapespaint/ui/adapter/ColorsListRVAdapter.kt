package ru.iandreyshev.compositeshapespaint.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_select_color.view.*
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import ru.iandreyshev.compositeshapespaint.ui.extension.inflate

class ColorsListRVAdapter(
        var onItemClick: (Color) -> Unit = {}
) : RecyclerView.Adapter<ColorViewRVHolder>() {

    private val mColors: Array<Color> by lazy { return@lazy Color.values() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewRVHolder {
        val view = parent.context.inflate(R.layout.item_select_color)
        return ColorViewRVHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewRVHolder, position: Int) {
        holder.initColor(mColors[position])
        holder.itemView.colorButton.setOnClickListener {
            onItemClick(mColors[position])
        }
    }

    override fun getItemCount(): Int = mColors.count()
}
