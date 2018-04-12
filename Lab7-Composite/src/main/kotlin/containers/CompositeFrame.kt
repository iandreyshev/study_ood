package containers

class CompositeFrame(private val frames: InnerFramesIterator) : IFrame {
    override val width: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val height: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val position: Vec2i
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

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

    interface InnerFramesIterator {
        fun forEach(action: IFrame.() -> Unit)
    }
}
