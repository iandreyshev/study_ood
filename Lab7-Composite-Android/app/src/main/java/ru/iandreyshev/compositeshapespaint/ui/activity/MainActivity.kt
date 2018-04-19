package ru.iandreyshev.compositeshapespaint.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.ui.adapter.ShapesListRVAdapter
import ru.iandreyshev.compositeshapespaint.viewModel.MainViewModel
import android.support.v7.widget.DividerItemDecoration
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.ui.adapter.AndroidCanvasAdapter

class MainActivity : AppCompatActivity() {
    private val mViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    private val mAdapter by lazy {
        ShapesListRVAdapter(
                { mViewModel.shapes.value },
                { mViewModel.setTarget(it) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel.shapes.observe(this, Observer { shapes ->
            mAdapter.notifyDataSetChanged()
            reDraw(shapes ?: return@Observer)
        })

        mViewModel.targetShape.observe(this, Observer { shape ->
            shapeInfoView.setShape(shape)
        })

        with(rvShapesList) {
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
        }

        refreshLayout.onRefresh {
            mViewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item ?: return false

        when (item.itemId) {
            R.id.action_resize -> {
                toast("Resize")
            }
            R.id.action_change_position -> {
                toast("Move")
            }
            R.id.action_change_stroke_size -> {
                toast("Resize strokeSize")
            }
            R.id.action_change_fill_color -> {
                toast("Change fill color")
            }
            R.id.action_change_stroke_color -> {
                toast("Change strokeSize color")
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun reDraw(shapes: List<IShape>) {
        cvCanvas.onDrawAction { canvas ->
            val adapter = AndroidCanvasAdapter(canvas)
            shapes.forEach { it.draw(adapter) }
        }
        cvCanvas.invalidate()
    }
}
