package shape

import canvas.Color

abstract class TermShape : IShape {
    override val composite: ICompositeShape? = null
    override var fillColor: Color? = null
        set(value) {
            value ?: return
            field = value
        }
    override var strokeColor: Color? = null
        set(value) {
            value ?: return
            field = value
        }
    override var strokeSize: Int? = null
        set(value) {
            value ?: return
            field = value
        }
}
