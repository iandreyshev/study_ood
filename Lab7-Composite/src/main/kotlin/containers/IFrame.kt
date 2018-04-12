package containers

interface IFrame {
    val width: Int

    val height: Int

    val position: Vec2i

    fun resizeFromLeft(value: Int)

    fun resizeFromRight(value: Int)

    fun resizeFromTop(value: Int)

    fun resizeFromBottom(value: Int)
}
