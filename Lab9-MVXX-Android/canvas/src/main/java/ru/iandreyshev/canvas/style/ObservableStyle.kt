package ru.iandreyshev.canvas.style

class ObservableStyle(
        style: IConstStyle,
        private val onUpdate: () -> Unit
) : Style(style) {

    override var fillColor: Color
        get() = super.fillColor
        set(value) {
            super.fillColor = value
            onUpdate()
        }

    override var strokeColor: Color
        get() = super.strokeColor
        set(value) {
            super.strokeColor = value
            onUpdate()}

    override var strokeSize: Float
        get() = super.strokeSize
        set(value) {
            super.strokeSize = value
            onUpdate()}

}
