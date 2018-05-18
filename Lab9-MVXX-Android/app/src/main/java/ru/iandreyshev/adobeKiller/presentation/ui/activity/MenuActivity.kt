package ru.iandreyshev.adobeKiller.presentation.ui.activity

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_menu.*
import ru.iandreyshev.adobeKiller.R
import ru.iandreyshev.adobeKiller.presentation.interactor.interfaces.IMenuInteractor
import ru.iandreyshev.adobeKiller.presentation.ui.adapter.CanvasesListRVAdapter
import ru.iandreyshev.adobeKiller.presentation.ui.dialog.DialogFactory
import ru.iandreyshev.adobeKiller.presentation.ui.extension.invisible
import ru.iandreyshev.adobeKiller.presentation.ui.extension.visible
import ru.iandreyshev.adobeKiller.presentation.ui.viewModel.MenuViewModel

class MenuActivity : BaseActivity<IMenuInteractor, MenuViewModel>(
        viewModelClass = MenuViewModel::class,
        layout = R.layout.activity_menu) {

    private val mCanvasesListAdapter: CanvasesListRVAdapter by lazy {
        CanvasesListRVAdapter().apply {
            onItemClick { interactor.openCanvas(it) }
        }
    }

    override val onProvideViewModel: MenuViewModel.() -> Unit = {
        canvases.observe { canvases ->
            if (canvases?.isNotEmpty() == true) {
                rvCanvases.visible()
                tvNotFound.invisible()
                mCanvasesListAdapter.setCanvases(canvases)
                return@observe
            }

            rvCanvases.invisible()
            tvNotFound.visible()
        }

        onOpen = {
            Log.e("Menu", "Open canvas!")
        }
    }

    override fun onLayoutCreated(savedInstanceState: Bundle?) {
        rvCanvases.adapter = mCanvasesListAdapter
        fabAddNew.setOnClickListener {
            DialogFactory.createCanvasDialog(this) { canvasName ->
                interactor.createCanvas(canvasName)
            }
        }
    }

}
