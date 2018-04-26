package ru.iandreyshev.gumballmachine.machine

import ru.iandreyshev.gumballmachine.machine.state.*

class GumballMachineWithStatePattern(
        private var startBallsCount: Int = 0
) : IGumballMachine {
    companion object {
        private const val MAX_BALLS_COUNT = Int.MAX_VALUE
        private const val MAX_INSERTED_COINS = 5
    }

    override var eventsHandler: IMachineEventsHandler = object : IMachineEventsHandler {}

    private var mBallsCount = startBallsCount
    private var mInsertedCoinsCount = 0
    private var mTotalCoinsCount = 0
    private lateinit var mCurrentState: MachineState

    private val mContext = GumballMachineContext()
    private val mHasCoinState: MachineState = HasCoinState(mContext, ::onError)
    private val mNoCoinState: MachineState = NoCoinState(mContext, ::onError)
    private val mSoldOutState: MachineState = SoldOutState(mContext, ::onError)
    private val mSoldState: MachineState = SoldState(mContext, ::onError)

    init {
        reset()
    }

    override val data: GumballMachineData
        get() = GumballMachineData(
                ballsCount = mBallsCount,
                insertedCoinsCount = mInsertedCoinsCount,
                totalCoinsCount = mTotalCoinsCount,
                maxCoinsCount = MAX_INSERTED_COINS)

    override fun fill(newBallsCount: Int) {
        mBallsCount += newBallsCount.coerceIn(0, MAX_BALLS_COUNT)

        when {
            mBallsCount <= 0 -> mContext.setSoldOutState()
            mInsertedCoinsCount <= 0 -> mContext.setNoCoinState()
            else -> mContext.setHasCoinState()
        }
    }

    override fun insertCoin() =
            mCurrentState.insertCoin()

    override fun ejectCoin() =
            mCurrentState.ejectCoin()

    override fun turnCrank() {
        mCurrentState.turnCrank()
        mCurrentState.dispense()
    }

    override fun reset() {
        mBallsCount = 0
        mInsertedCoinsCount = 0
        mTotalCoinsCount = 0
        fill(startBallsCount)
    }

    private fun onError(error: GumballMachineError) {
        eventsHandler.onError(error)
    }

    inner class GumballMachineContext : IGumballMachineContext {
        override val data: GumballMachineData
            get() = this@GumballMachineWithStatePattern.data

        override fun releaseBall() {
            --mBallsCount
            eventsHandler.onReleaseBall()
        }

        override fun insertCoin() {
            ++mInsertedCoinsCount
        }

        override fun releaseCoins() {
            while (mInsertedCoinsCount > 0) {
                --mInsertedCoinsCount
            }
            eventsHandler.onReleaseCoins()
        }

        override fun takeCoin() {
            --mInsertedCoinsCount
            ++mTotalCoinsCount
        }

        override fun fill(newBallsCount: Int) {
            mBallsCount = newBallsCount
        }

        override fun setSoldOutState() {
            mCurrentState = mSoldOutState
        }

        override fun setNoCoinState() {
            mCurrentState = mNoCoinState
        }

        override fun setSoldState() {
            mCurrentState = mSoldState
        }

        override fun setHasCoinState() {
            mCurrentState = mHasCoinState
        }
    }
}
