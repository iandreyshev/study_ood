package ru.iandreyshev.adobeKiller.presentation.drawing.frame

import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.Frame
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IConstFrame

class ObservableFrame(
        frame: IConstFrame,
        private val onUpdate: () -> Unit
) : Frame(frame) {

    override var position: Vec2f
        get() = super.position
        set(value) {
            super.position = value
            onUpdate()
        }

    override fun resize(newWidth: Float, newHeight: Float) {
        super.resize(newWidth, newHeight)
        onUpdate()
    }
}
