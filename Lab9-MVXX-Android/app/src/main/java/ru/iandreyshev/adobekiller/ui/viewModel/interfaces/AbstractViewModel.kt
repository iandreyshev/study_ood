package ru.iandreyshev.adobekiller.ui.viewModel.interfaces

import android.arch.lifecycle.AndroidViewModel
import ru.iandreyshev.adobekiller.app.AdobeKillerApp
import ru.iandreyshev.adobekiller.interactor.interfaces.IInteractor

abstract class AbstractViewModel<TInteractor : IInteractor> : AndroidViewModel(
        AdobeKillerApp.instance
), IViewModel {

    var interactor: TInteractor? = null
}
