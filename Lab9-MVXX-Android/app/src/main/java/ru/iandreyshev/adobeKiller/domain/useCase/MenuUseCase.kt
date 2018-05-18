package ru.iandreyshev.adobeKiller.domain.useCase

import android.util.Log
import ru.iandreyshev.adobeKiller.data.DbCanvas
import ru.iandreyshev.adobeKiller.data.IRepository
import ru.iandreyshev.adobeKiller.domain.useCase.interfaces.IMenuUseCase
import ru.iandreyshev.adobeKiller.domain.model.CanvasData
import ru.iandreyshev.adobeKiller.presentation.presenter.interfaces.IMenuPresenter

class MenuUseCase(
        private val repository: IRepository,
        private val presenter: IMenuPresenter
) : IMenuUseCase {

    init {
        presenter.setCanvases(repository.getCanvasesData())
    }

    override fun createCanvas(name: String) {
        repository.createCanvas(name)
        val canvases = repository.getCanvasesData()
        presenter.setCanvases(canvases)
        Log.e("Get canvases", canvases.size.toString())
    }

    override fun openCanvas(canvasData: CanvasData) =
            presenter.openCanvas(canvasData)

    private fun DbCanvas.toDomainModel(): CanvasData =
            CanvasData(
                    id = id,
                    name = name
            )

    private fun IRepository.getCanvasesData(): List<CanvasData> =
            getCanvases().map { it.toDomainModel() }

}
