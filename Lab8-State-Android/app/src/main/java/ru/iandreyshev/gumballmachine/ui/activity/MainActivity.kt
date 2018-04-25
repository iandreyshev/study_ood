package ru.iandreyshev.gumballmachine.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_fill.view.*
import kotlinx.android.synthetic.main.lay_metrics.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import pl.bclogic.pulsator4droid.library.PulsatorLayout
import ru.iandreyshev.gumballmachine.R
import ru.iandreyshev.gumballmachine.interactor.interfaces.IMachineInteractor
import ru.iandreyshev.gumballmachine.viewModel.interfaces.IMachineViewModel

class MainActivity : BaseActivity<IMachineInteractor, IMachineViewModel>(
        IMachineViewModel::class,
        R.layout.activity_main) {

    override fun onProvideViewModel(viewModel: IMachineViewModel) {
        with(viewModel) {
            ballsCount.observe(this@MainActivity::updateBallsCount)
            insertedCoinsCount.observe(this@MainActivity::updateInsertedCoinsCount)
            totalCoinsCount.observe(this@MainActivity::updateTotalCoinsCount)
            onErrorListener = { handleError(it) }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        btnInsertCoin.setOnClickListener {
            interactor?.insertCoin()
            plsInsertCoin.restart()
        }

        btnReleaseCoin.setOnClickListener {
            interactor?.removeCoin()
            plsReleaseCoin.restart()
        }

        btnFill.setOnClickListener {
            plsFill.restart()
            alert {
                title = "Fill the machine"
                val view = LayoutInflater.from(this@MainActivity)
                        .inflate(R.layout.dialog_fill, null)
                customView = view
                isCancelable = false
                positiveButton("Fill") {
                    interactor?.fill(view.sbBallsCount.progress)
                }
                negativeButton("Cancel") { it.dismiss() }
            }.show()
        }

        btnReleaseBall.setOnClickListener {
            interactor?.turnCrank()
            plsReleaseBall.restart()
        }

        layRefresh.setOnRefreshListener {
            interactor?.reset()
            layRefresh.isRefreshing = false
        }
    }

    private fun updateBallsCount(value: Int?) {
        mvBalls.value = value.toString()
    }

    private fun updateInsertedCoinsCount(value: Int?) {
        mvInsertedCoins.value = value.toString()
    }

    private fun updateTotalCoinsCount(value: Int?) {
        mvTotalCoins.value = value.toString()
    }

    private fun handleError(message: String) {
        alert(message) {
            title = "Error"
            isCancelable = false
            okButton { it.dismiss() }
        }.show()
    }

    private fun PulsatorLayout.restart() {
        stop()
        start()
    }
}
