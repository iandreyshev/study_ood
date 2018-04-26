package ru.iandreyshev.gumballmachine.machine

class EnumUsingGumballMachine(
        private val startBallsCount: Int
) : IGumballMachine {

    companion object {
        private const val MAX_BALLS_COUNT = Int.MAX_VALUE
        private const val MAX_INSERTED_COINS = 5
    }

    override var errorHandler = fun(_: GumballMachineError) {}

    override val data: GumballMachineData
        get() = GumballMachineData(
                name = "Enum using",
                ballsCount = mBallsCount,
                insertedCoinsCount = mInsertedCoinsCount,
                totalCoinsCount = mTotalCoinsCount,
                maxCoinsCount = MAX_INSERTED_COINS)

    private var mCurrentState = State.NO_COIN
    private var mBallsCount = startBallsCount
    private var mInsertedCoinsCount = 0
    private var mTotalCoinsCount = 0

    private enum class State {
        SOLD,
        SOLD_OUT,
        NO_COIN,
        HAS_COIN
    }

    init {
        reset()
    }

    override fun insertCoin() = stateAction {
        onSold {
            error { message = "Please wait, we're already giving you a gumball" }
        }

        onSoldOut {
            error { message = "You can't insert a quarter, the machine is sold out" }
        }

        onNoCoin {
            if (mInsertedCoinsCount == MAX_INSERTED_COINS) {
                error { message = "Inserted max coins" }
                return@onNoCoin
            }
            ++mInsertedCoinsCount
            switchTo(State.HAS_COIN)
        }

        onHasCoin {
            if (mInsertedCoinsCount == MAX_INSERTED_COINS) {
                error { message = "Inserted max coins" }
                return@onHasCoin
            }
            if (mInsertedCoinsCount < MAX_INSERTED_COINS) {
                ++mInsertedCoinsCount
            }
        }
    }

    override fun ejectCoin() = stateAction {
        onSold {
            error { message = "Sorry you already turned the crank" }
        }

        onSoldOut {
            if (mInsertedCoinsCount <= 0) {
                error { message = "You can't eject, you haven't inserted a quarter yet" }
                return@onSoldOut
            }
            while (mInsertedCoinsCount > 0) {
                --mInsertedCoinsCount
            }
        }

        onNoCoin {
            error { message = "Coins not inserted yet" }
        }

        onHasCoin {
            mInsertedCoinsCount = 0
            switchTo(State.NO_COIN)
        }
    }

    override fun turnCrank() = stateAction {
        onSold {
            error { message = "Turning twice doesn't get you another gumball" }
        }

        onSoldOut {
            error { message = "You turned but there's no gumballs" }
        }

        onNoCoin {
            error { message = "Please insert coin to get the ball" }
        }

        onHasCoin {
            --mInsertedCoinsCount
            ++mTotalCoinsCount
            switchTo(State.SOLD)

            --mBallsCount
            identifyState()
        }
    }

    override fun fill(ballsCount: Int) = stateAction {
        fun safeFill() {
            if (MAX_BALLS_COUNT - ballsCount >= mBallsCount) {
                mBallsCount += ballsCount
            } else {
                error { message = "Balls count is max" }
            }

            identifyState()
        }

        onSold {
            error { message = "Can`t to insert balls during release balls" }
        }
        onSoldOut { safeFill() }
        onNoCoin { safeFill() }
        onHasCoin { safeFill() }
    }

    override fun reset() {
        mBallsCount = 0
        mInsertedCoinsCount = 0
        mTotalCoinsCount = 0
        fill(startBallsCount)
    }

    private fun switchTo(state: State) {
        mCurrentState = state
    }

    private fun identifyState() = when {
        mBallsCount <= 0 -> State.SOLD_OUT
        mInsertedCoinsCount <= 0 -> State.NO_COIN
        else -> State.HAS_COIN
    }.let { switchTo(it) }

    private fun stateAction(action: StateEventBuilder.() -> Unit) =
            StateEventBuilder().apply(action).create()()

    private fun error(errorAction: GumballMachineError.() -> Unit) {
        val error = GumballMachineError()
        errorAction(error)
        errorHandler(error)
    }

    private inner class StateEventBuilder {
        private var mOnSold: (() -> Unit)? = null
        private var mOnSoldOut: (() -> Unit)? = null
        private var mOnNoCoin: (() -> Unit)? = null
        private var mOnHasCoin: (() -> Unit)? = null

        fun onSold(action: () -> Unit) {
            mOnSold = action
        }

        fun onSoldOut(action: () -> Unit) {
            mOnSoldOut = action
        }

        fun onNoCoin(action: () -> Unit) {
            mOnNoCoin = action
        }

        fun onHasCoin(action: () -> Unit) {
            mOnHasCoin = action
        }

        fun create(): (() -> Unit) = {
            when (mCurrentState) {
                State.SOLD -> mOnSold
                State.SOLD_OUT -> mOnSoldOut
                State.NO_COIN -> mOnNoCoin
                State.HAS_COIN -> mOnHasCoin
            }?.invoke()
        }
    }
}
