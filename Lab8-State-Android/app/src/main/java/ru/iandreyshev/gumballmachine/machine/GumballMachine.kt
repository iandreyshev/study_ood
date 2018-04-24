package ru.iandreyshev.gumballmachine.machine

import ru.iandreyshev.gumballmachine.machine.state.*

class GumballMachine(
        private var startBallsCount: Int = 0
) : IGumballMachine {
    companion object {
        private const val MAX_BALLS_COUNT = Int.MAX_VALUE
        private const val MAX_INSERTED_QUARTERS = 5
    }

    override var eventsHandler: IMachineEventsHandler = object : IMachineEventsHandler {}

    private var mBallsCount = startBallsCount
    private var mInsertedQuartersCount = 0
    private var mTotalQuartersCount = 0
    private lateinit var mCurrentState: MachineState

    private val mContext = GumballMachineContext()
    private val mHasQuarterState: MachineState = HasQuarterState(mContext, ::onError)
    private val mNoQuarterState: MachineState = NoQuarterState(mContext, ::onError)
    private val mSoldOutState: MachineState = SoldOutState(mContext, ::onError)
    private val mSoldState: MachineState = SoldState(mContext, ::onError)

    init {
        reset()
    }

    override val data: GumballMachineData
        get() = GumballMachineData(
                ballsCount = mBallsCount,
                insertedQuartersCount = mInsertedQuartersCount,
                totalQuartersCount = mTotalQuartersCount,
                maxQuartersCount = MAX_INSERTED_QUARTERS)

    override fun fill(newBallsCount: Int) {
        mBallsCount = newBallsCount.coerceIn(0, MAX_BALLS_COUNT)

        if (mBallsCount <= 0) {
            mContext.setSoldOutState()
        } else {
            mContext.setNoQuarterState()
        }
    }

    override fun insertQuarter() =
            mCurrentState.insertQuarter()

    override fun ejectQuarter() =
            mCurrentState.ejectQuarter()

    override fun turnCrank() {
        mCurrentState.turnCrank()
        mCurrentState.dispense()
    }

    override fun reset() {
        mBallsCount = startBallsCount
        mInsertedQuartersCount = 0
        mTotalQuartersCount = 0
        fill(mBallsCount)
    }

    private fun onError(error: GumballMachineError) {
        eventsHandler.onError(error)
    }

    inner class GumballMachineContext : IGumballMachineContext {
        override val data: GumballMachineData
            get() = this@GumballMachine.data

        override fun releaseBall() {
            --mBallsCount
            eventsHandler.onReleaseBall()
        }

        override fun insertQuarter() {
            ++mInsertedQuartersCount
        }

        override fun releaseQuarter() {
            --mInsertedQuartersCount
            eventsHandler.onReleaseQuarter()
        }

        override fun setSoldOutState() {
            mCurrentState = mSoldOutState
        }

        override fun setNoQuarterState() {
            mCurrentState = mNoQuarterState
        }

        override fun setSoldState() {
            mCurrentState = mSoldState
        }

        override fun setHasQuarterState() {
            mCurrentState = mHasQuarterState
        }
    }
}
