package ru.iandreyshev.geometry.frame

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.Test
import pl.mareklangiewicz.uspek.USpek
import pl.mareklangiewicz.uspek.USpek.o
import ru.iandreyshev.geometry.vector.Vec2f

class ObservableFrameTest {

    @Test
    fun test() {
        USpek.uspek("Observable frame test") {

            "Notify if" o {
                val observerMock: (IConstFrame) -> Unit = mock()
                val instance = Frame()
                val frame = ObservableFrame(instance, observerMock)

                "Width changed" o {
                    frame.resize(frame.width + 100f, frame.height)
                    verify(observerMock).invoke(any())
                    verifyNoMoreInteractions(observerMock)
                }

                "Height changed" o {
                    frame.resize(frame.width, frame.height + 100f)
                    verify(observerMock).invoke(any())
                    verifyNoMoreInteractions(observerMock)
                }

                "Position changed" o {
                    frame.position = Vec2f()
                    verify(observerMock).invoke(any())
                    verifyNoMoreInteractions(observerMock)
                }
            }

        }
    }

}
