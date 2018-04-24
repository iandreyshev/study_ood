package ru.iandreyshev.gumballmachine.ui.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
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
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        btnInsertQuarter.setOnClickListener {
            pulsInsertQuarter.restart()
        }

        btnReleaseQuarter.setOnClickListener {
            pulsReleaseQuarter.restart()
        }

        btnReleaseBall.setOnClickListener {
            pulsReleaseBall.restart()
        }
    }

    private fun updateBallsCount(value: Int?) {
    }

    private fun updateInsertedQuartersCount(value: Int?) {
    }

    private fun updateTotalQuartersCount(value: Int?) {
    }

    private fun PulsatorLayout.restart() {
        stop()
        start()
    }
}
