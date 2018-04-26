package ru.iandreyshev.gumballmachine.machine

import ru.iandreyshev.gumballmachine.machine.state.*

class StateUsingGumballMachine(
        private var startBallsCount: Int
) : IGumballMachine {

    companion object {
        private const val MAX_BALLS_COUNT = Int.MAX_VALUE
        private const val MAX_INSERTED_COINS = 5
    }

    override var errorHandler = fun(_: GumballMachineError) {}

    private var mBallsCount = startBallsCount
    private var mInsertedCoinsCount = 0
    private var mTotalCoinsCount = 0

    private val mContext = GumballMachineContext()
    private val mHasCoinState: MachineState = HasCoinState(mContext, ::onError)
    private val mNoCoinState: MachineState = NoCoinState(mContext, ::onError)
    private val mSoldOutState: MachineState = SoldOutState(mContext, ::onError)
    private val mSoldState: MachineState = SoldState(mContext, ::onError)
    private var mCurrentState: MachineState = mNoCoinState

    init {
        reset()
    }

    override val data: GumballMachineData
        get() = GumballMachineData(
                name = "State pattern",
                ballsCount = mBallsCount,
                insertedCoinsCount = mInsertedCoinsCount,
                totalCoinsCount = mTotalCoinsCount,
                maxCoinsCount = MAX_INSERTED_COINS)

    override fun fill(ballsCount: Int) =
            mCurrentState.fill(ballsCount)

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

    private fun onError(error: GumballMachineError) =
            errorHandler(error)

    inner class GumballMachineContext : IGumballMachineContext {
        override val data: GumballMachineData
            get() = this@StateUsingGumballMachine.data

        override fun releaseBall() {
            --mBallsCount
        }

        override fun insertCoin() {
            ++mInsertedCoinsCount
        }

        override fun releaseCoins() {
            while (mInsertedCoinsCount > 0) {
                --mInsertedCoinsCount
            }
        }

        override fun takeCoin() {
            --mInsertedCoinsCount
            ++mTotalCoinsCount
        }

        override fun fill(ballsCount: Int) {
            mBallsCount += ballsCount.coerceIn(0, MAX_BALLS_COUNT)

            when {
                mBallsCount <= 0 -> mContext.setSoldOutState()
                mInsertedCoinsCount <= 0 -> mContext.setNoCoinState()
                else -> mContext.setHasCoinState()
            }
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
