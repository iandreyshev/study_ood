package containers

class Frame(
        override var position: Vec2i,
        override var width: Int,
        override var height: Int
) : AbstractFrame() {
    override fun onResize(newWidth: Int, newHeight: Int) {
        width = newWidth
        height = newHeight
    }
}
