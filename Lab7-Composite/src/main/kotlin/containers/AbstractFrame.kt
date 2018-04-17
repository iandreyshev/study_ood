package containers

abstract class AbstractFrame {
    abstract var position: Vec2i

    abstract val width: Int

    abstract val height: Int

    fun resize(newWidth: Int, newHeight: Int) {
        if (newWidth < 0 || newHeight < 0) {
            throw IllegalArgumentException("Invalid frame size. Any size less than zero.")
        }
        onResize(newWidth, newHeight)
    }

    protected abstract fun onResize(newWidth: Int, newHeight: Int)
}
