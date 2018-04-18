package shape

import containers.Vec2i
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CompositeShapeTest {
    private lateinit var composite: CompositeShape

    @Before
    fun setup() {
        composite = CompositeShape()
    }

    @Test
    fun compositeReturnItselfOnGetComposite() {
        assertEquals(composite, composite.composite)
    }

    @Test
    fun returnDefaultPositionIfEmpty() {
        val position = composite.frame.position

        assertEquals(0, position.x)
        assertEquals(0, position.y)
    }

    @Test
    fun returnTopLeftOnGetPositionWithOneInnerShape() {
        val rectTopLeft = Vec2i(-10, -10)
        val rectRightBottom = Vec2i(10, 10)
        composite.add(Rectangle(rectTopLeft, rectRightBottom))

        assertEquals(rectTopLeft, composite.frame.position)
    }

    @Test
    fun returnTopLeftOnGetPositionWithSeveralInnerShapes() {
        val rect1TopLeft = Vec2i(-10, -10)
        val rect1RightBottom = Vec2i(10, 10)

        val rect2TopLeft = Vec2i(-8, -100)
        val rect2RightBottom = Vec2i(200, 8)

        composite.add(Rectangle(rect1TopLeft, rect1RightBottom))
        composite.add(Rectangle(rect2TopLeft, rect2RightBottom))

        assertEquals(Vec2i(rect1TopLeft.x, rect2TopLeft.y), composite.frame.position)
    }

    @Test
    fun returnZeroSizeWithoutInnerShapes() {
        assertEquals(0, composite.frame.width)
        assertEquals(0, composite.frame.height)
    }

    @Test
    fun returnSumSizeOfTheInnerShapes() {

    }
}
