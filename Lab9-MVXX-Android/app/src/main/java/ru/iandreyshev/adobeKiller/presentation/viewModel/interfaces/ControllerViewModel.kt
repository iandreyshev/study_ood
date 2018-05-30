package ru.iandreyshev.adobeKiller.presentation.viewModel.interfaces

import android.arch.lifecycle.ViewModel
import ru.iandreyshev.adobeKiller.app.ViewControllerType
import ru.iandreyshev.adobeKiller.domain.controller.interfaces.IViewController

abstract class ControllerViewModel<TController : IViewController>(
        val viewControllerType: ViewControllerType
) : ViewModel(), IViewModel {

    var controller: TController? = null

}
