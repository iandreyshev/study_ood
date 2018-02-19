package display

import com.sun.javafx.geom.Vec2d
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ExtensionTest {
    @Test
    fun normalizeZeroVector() {
        val zeroVec = Vec2d(.0, .0)
        zeroVec.normalize()

        assertEquals(.0, zeroVec.x)
        assertEquals(.0, zeroVec.y)
    }

    @Test
    fun normalizePositiveVector() {
        val vec = Vec2d(2.0, 1.0)
        vec.normalize()

        assertEquals(1.0, vec.x)
        assertEquals(0.5, vec.y)
    }

    @Test
    fun normalizeNegativeVector() {
        val vec = Vec2d(-2.0, -1.0)
        vec.normalize()

        assertEquals(-1.0, vec.x)
        assertEquals(-0.5, vec.y)
    }
}
