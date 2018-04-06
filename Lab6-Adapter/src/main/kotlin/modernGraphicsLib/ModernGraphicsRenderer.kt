package modernGraphicsLib

open class ModernGraphicsRenderer(private val output: (String) -> Unit) {
    private var mIsDrawingBegin = false

    fun beginDraw() {
        if (mIsDrawingBegin) {
            throw IllegalStateException("Drawing already begin")
        }

        mIsDrawingBegin = true
        output("beginDraw")
    }

    fun endDraw() {
        if (!mIsDrawingBegin) {
            throw IllegalStateException("Drawing already stopped")
        }

        mIsDrawingBegin = false
        output("endDraw")
    }

    fun drawLine(from: ModernPoint, to: ModernPoint, color: RGBAColor) {
        if (!mIsDrawingBegin) {
            throw IllegalStateException("Can not draw before begin draw")
        }

        output("drawLine from $from to $to")
    }
}
