package modernGraphicsLib

open class ModernGraphicsRenderer {
    private var mIsDrawingBegin = false

    fun beginDraw() {
        if (mIsDrawingBegin) {
            throw IllegalStateException("Drawing already begin")
        }

        mIsDrawingBegin = true
    }

    fun endDraw() {
        if (!mIsDrawingBegin) {
            throw IllegalStateException("Drawing already stopped")
        }

        mIsDrawingBegin = false
    }

    fun drawLine(from: ModernPoint, to: ModernPoint, color: RGBAColor) {

    }
}
