package containers

class SimpleFrame(
        override val position: Vec2i,
        override var width: Int,
        override var height: Int
) : IFrame {
    override fun resizeFromLeft(value: Int) {
        TODO("resizeFromLeft not implemented")
    }

    override fun resizeFromRight(value: Int) {
        TODO("resizeFromRight not implemented")
    }

    override fun resizeFromTop(value: Int) {
        TODO("resizeFromTop not implemented")
    }

    override fun resizeFromBottom(value: Int) {
        TODO("resizeFromBottom not implemented")
    }
}
