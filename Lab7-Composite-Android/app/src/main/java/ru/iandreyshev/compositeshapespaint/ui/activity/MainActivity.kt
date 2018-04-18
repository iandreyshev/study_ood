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
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.support.v4.onRefresh

class MainActivity : AppCompatActivity() {
    private val mViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    private val mAdapter by lazy {
        ShapesListRVAdapter(
                { mViewModel.shapes.value?.toList() ?: listOf() },
                { shape -> mViewModel.setTarget(shape) }
        )
    }
    private val mLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel.shapes.observe(this, Observer {
            mAdapter.notifyDataSetChanged()
        })

        mViewModel.targetShape.observe(this, Observer { shape ->
            shapeInfoView.setShape(shape ?: return@Observer)
        })

        with(rvShapesList) {
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(context, mLayoutManager.orientation))
        }

        refreshLayout.onRefresh { mViewModel.refresh() }
    }
}
