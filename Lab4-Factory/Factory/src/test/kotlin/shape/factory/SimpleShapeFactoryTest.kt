package shape.factory

import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import shape.Ellipse
import shape.Rectangle
import shape.RegularPolygon
import shape.Triangle

class SimpleShapeFactoryTest {
    private lateinit var mFactory: IShapeFactory

    @Before
    fun setup() {
        mFactory = ShapeFactory()
    }

    @Test
    fun returnNullIfArgsNotValid() {
        val args = listOf("¯\\_(ツ)_/¯ ", "first", "second")
        val shape = mFactory.create(args)

        assertNull(shape)
    }

    @Test
    fun canCreateRectangle() {
        val args = listOf("rectangle", "0", "0", "0", "0")
        val shape = mFactory.create(args)

        assertTrue(shape is Rectangle)
    }

    @Test
    fun canCreateEllipse() {
        val args = listOf("ellipse", "0", "0", "10", "10")
        val shape = mFactory.create(args)

        assertTrue(shape is Ellipse)
    }

    @Test
    fun canCreateRegularPolygon() {
        val args = listOf("polygon", "0", "0", "3", "0")
        val shape = mFactory.create(args)

        assertTrue(shape is RegularPolygon)
    }

    @Test
    fun canCreateTriangle() {
        val args = listOf("triangle", "1", "2", "3", "4", "5", "6")
        val shape = mFactory.create(args)

        assertTrue(shape is Triangle)
    }
}
