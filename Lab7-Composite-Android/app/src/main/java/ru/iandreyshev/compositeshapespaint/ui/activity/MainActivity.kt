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
import canvas.Color
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_shape_info.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.ui.ActionError
import ru.iandreyshev.compositeshapespaint.ui.adapter.AndroidCanvasAdapter
import ru.iandreyshev.compositeshapespaint.ui.dialog.ActionDialog

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
            shapes ?: return@Observer
            reDraw(shapes)
        })

        mViewModel.targetShape.observe(this, Observer { shape ->
            shapeInfoView.setShape(shape)
        })

        mViewModel.actionError.observe(this, Observer { error ->
            handleActionError(error)
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
        when (item?.itemId) {
            R.id.act_resize -> ActionDialog(this, R.string.act_resize, mViewModel::resize)
            R.id.act_move -> ActionDialog(this, R.string.act_move, mViewModel::move)
            R.id.act_resize_stroke -> ActionDialog(this, R.string.act_resize_stroke, mViewModel::resizeStroke)
            R.id.act_fill_color -> ActionDialog(this, R.string.act_fill_color, mViewModel::changeFillColor)
            R.id.act_stroke_color -> ActionDialog(this, R.string.act_stroke_color, mViewModel::changeStrokeColor)
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

    private fun handleActionError(error: ActionError?) {
        when (error) {
            ActionError.SHAPE_NOT_SELECTED -> toast(R.string.action_error_null_shape)
            ActionError.RESIZE_ARGS_ERR -> toast("Use only float values separated by space")
            ActionError.MOVE_ARGS_ERR -> toast("Use only float values separated by space")
            ActionError.RESIZE_STROKE_ERR -> toast("Use only positive float values")
            ActionError.INVALID_COLOR -> toast("Available colors: ${Color.values().joinToString()}")
            ActionError.UNDEFINED_ERR -> toast(R.string.action_error_undefined)
        }
    }
}
