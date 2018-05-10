package ru.iandreyshev.gumballmachine.machine

import com.nhaarman.mockito_kotlin.*
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GumballMachineTest {

    @Test
    fun haveBallsByDefault() =
            testWrap(startBallsCount = 10) {
                assertEquals(10, data.ballsCount)
            }

    @Test
    fun haveZeroCoinsByDefault() =
            testWrap {
                assertEquals(0, data.totalCoinsCount)
                assertEquals(0, data.insertedCoinsCount)
            }

    @Test
    fun haveSoldOutStateByDefault() =
            testWrap {
                assertEquals(StateName.SOLD_OUT, data.stateName)
            }

    @Test
    fun changeStateToNoCoinAfterFill() =
            testWrap {
                assertEquals(StateName.SOLD_OUT, data.stateName)
                fill(1)
                assertEquals(StateName.NO_COIN, data.stateName)
            }

    @Test
    fun changeStateToSoldOutAfterSoldAllBalls() =
            testWrap(startBallsCount = 1) {
                assertEquals(StateName.NO_COIN, data.stateName)
                insertCoin()
                turnCrank()
            }

    @Test
    fun canIncrementTotalCoinsAfterBuyBalls() =
            testWrap(startBallsCount = 1) {
                assertEquals(0, data.totalCoinsCount)
                insertCoin()
                turnCrank()
                assertEquals(1, data.totalCoinsCount)
            }

    @Test
    fun saveInsertedCoinsAfterSoldOut() =
            testWrap(startBallsCount = 1) {
                insertCoin()
                insertCoin()
                turnCrank()
                assertEquals(StateName.SOLD_OUT, data.stateName)
                assertEquals(1, data.insertedCoinsCount)
            }

    @Test
    fun callErrorHandlerIfTurnCrankBeforeInsertCoin() {
        val errorHandlerMock = newErrorHandlerMock()

        testWrap(startBallsCount = 1, errHandler = errorHandlerMock) {
            turnCrank()
        }

        verify(errorHandlerMock, times(3)).invoke(any())
    }

    @Test
    fun callErrorHandlerIfInsertMoreThanFiveCoins() {
        val errorHandlerMock = newErrorHandlerMock()

        testWrap(startBallsCount = 1, errHandler = errorHandlerMock) {
            repeat(6) {
                insertCoin()
                print(data.insertedCoinsCount)
            }
        }

        verify(errorHandlerMock, times(2)).invoke(any())
    }

    @Test
    fun callErrorHandlerIfInsertCoinsInSoldOutState() {
        val errorHandlerMock = newErrorHandlerMock()

        testWrap(errHandler = errorHandlerMock) {
            insertCoin()
        }

        verify(errorHandlerMock, times(2)).invoke(any())
    }

    @Test
    fun canReturnCoins() =
            testWrap(startBallsCount = 1) {
                repeat(3) {
                    insertCoin()
                }
                assertEquals(3, data.insertedCoinsCount)
                repeat(3) {
                    ejectCoin()
                }
                assertEquals(0, data.insertedCoinsCount)
            }

    @Test
    fun canReturnCoinsAfterSoldOut() {
        val errorHandler = newErrorHandlerMock()

        testWrap(startBallsCount = 2, errHandler = errorHandler) {
            repeat(3) {
                insertCoin()
            }
            turnCrank()
            turnCrank()
            assertEquals(0, data.ballsCount)
            assertEquals(1, data.insertedCoinsCount)
            ejectCoin()
            assertEquals(0, data.insertedCoinsCount)
        }

        verifyNoMoreInteractions(errorHandler)
    }

    @Test
    fun canFillBalls() {
        testWrap {
            assertEquals(0, data.ballsCount)
            fill(10)
            assertEquals(10, data.ballsCount)
        }
    }

    private fun testWrap(
            startBallsCount: Int = 0,
            errHandler: (GumballMachineError) -> Unit = {},
            test: IGumballMachine.() -> Unit) {
        EnumUsingGumballMachine(startBallsCount).apply {
            errorHandler = errHandler
            test(this)
        }
        StateUsingGumballMachine(startBallsCount).apply {
            errorHandler = errHandler
            test(this)
        }
    }

    private fun newErrorHandlerMock(): (GumballMachineError) -> Unit = mock()
}
