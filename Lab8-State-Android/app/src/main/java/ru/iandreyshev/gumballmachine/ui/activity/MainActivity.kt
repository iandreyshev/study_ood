package ru.iandreyshev.gumballmachine.ui.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
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
            insertedQuartersCount.observe(this@MainActivity::updateInsertedQuartersCount)
            totalQuartersCount.observe(this@MainActivity::updateTotalQuartersCount)
            onErrorListener = { handleError(it) }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        btnInsertQuarter.setOnClickListener {
            interactor?.insertQuarter()
            plsInsertQuarter.restart()
        }

        btnReleaseQuarter.setOnClickListener {
            interactor?.removeQuarter()
            plsReleaseQuarter.restart()
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

    private fun updateInsertedQuartersCount(value: Int?) {
        mvInsertedQuarters.value = value.toString()
    }

    private fun updateTotalQuartersCount(value: Int?) {
        mvTotalQuarters.value = value.toString()
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
