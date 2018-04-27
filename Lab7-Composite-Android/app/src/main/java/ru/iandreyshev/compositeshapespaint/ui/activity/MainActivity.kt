package ru.iandreyshev.compositeshapespaint.ui.activity

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import ru.iandreyshev.compositeshapespaint.R
import ru.iandreyshev.compositeshapespaint.ui.adapter.ShapesListRVAdapter
import ru.iandreyshev.compositeshapespaint.viewModel.MainViewModel
import android.view.Menu
import android.view.MenuItem
import ru.iandreyshev.compositeshapespaint.model.canvas.Color
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_shape_info.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast
import ru.iandreyshev.compositeshapespaint.interactor.interfaces.IMainInteractor
import ru.iandreyshev.compositeshapespaint.model.shape.IShape
import ru.iandreyshev.compositeshapespaint.ui.ActionError
import ru.iandreyshev.compositeshapespaint.ui.adapter.AndroidCanvasAdapter
import ru.iandreyshev.compositeshapespaint.ui.dialog.ActionDialog
import ru.iandreyshev.compositeshapespaint.factory.CleanArchitectureFactory
import ru.iandreyshev.compositeshapespaint.ui.dialog.DialogFactory

class MainActivity : BaseActivity<IMainInteractor, MainViewModel>(
        layout = R.layout.activity_main,
        viewModelClass = MainViewModel::class,
        viewModelFactory = CleanArchitectureFactory) {

    private val mAdapter: ShapesListRVAdapter by lazy {
        ShapesListRVAdapter({ interactor?.selectShape(it) })
    }

    override val onSubscribeToViewModel: MainViewModel.() -> Unit = {
        shapes.observe {
            val shapes = it ?: listOf()
            mAdapter.shapes = shapes
            reDraw(shapes)
        }
        targetShape.observe {
            shapeInfoView.setShape(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        refreshLayout.onRefresh {
            interactor?.refresh()
            refreshLayout.isRefreshing = false
        }

        with(rvShapesList) {
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.act_resize -> DialogFactory.shapeSizeDialog(this) {
            }
            R.id.act_move -> ActionDialog(this, R.string.act_move, {})
            R.id.act_resize_stroke -> DialogFactory.strokeSizeDialog(this) {
            }
            R.id.act_fill_color -> DialogFactory.fillColorDialog(this) {
            }
            R.id.act_stroke_color -> DialogFactory.strokeColorDialog(this) {
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
