package ru.iandreyshev.gumballmachine.ui.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_fill.view.*
import kotlinx.android.synthetic.main.lay_metrics.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import ru.iandreyshev.gumballmachine.R
import ru.iandreyshev.gumballmachine.factory.CleanArchitectureFactory
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMainInteractor
import ru.iandreyshev.gumballmachine.ui.extension.inflate
import ru.iandreyshev.gumballmachine.ui.extension.onClick
import ru.iandreyshev.gumballmachine.viewModel.MainViewModel

class MainActivity : BaseActivity<IMainInteractor, MainViewModel>(
        CleanArchitectureFactory,
        MainViewModel::class,
        R.layout.activity_main) {

    override fun onProvideViewModel(viewModel: MainViewModel) {
        with(viewModel) {
            onErrorListener = { handleError(it) }
            ballsCount.observe { mvBalls.value = it.toString() }
            totalCoinsCount.observe { mvTotalCoins.value = it.toString() }
            insertedCoinsCount.observe { mvInsertedCoins.value = it.toString() }
            machineName.observe { tvMachineName.text = it }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        btnInsertCoin.onClick(plsInsertCoin) {
            interactor?.insertCoin()
        }

        btnReleaseCoin.onClick(plsReleaseCoin) {
            interactor?.removeCoin()
        }

        btnReleaseBall.onClick(plsReleaseBall) {
            interactor?.turnCrank()
        }

        btnSwitchMachine.onClick(plsSwitchMachine) {
            interactor?.switchMachine()
        }

        btnFill.onClick(plsFill) {
            alert {
                title = "Fill the machine"
                val view = inflate(R.layout.dialog_fill)
                customView = view
                positiveButton("Fill") { interactor?.fill(view.sbBallsCount.progress) }
                negativeButton("Cancel") { it.dismiss() }
            }.show()
        }

        layRefresh.setOnRefreshListener {
            interactor?.reset()
            layRefresh.isRefreshing = false
        }
    }

    private fun handleError(message: String) {
        alert(message) {
            title = "Error"
            okButton { it.dismiss() }
        }.show()
    }
}
