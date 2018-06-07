package ru.iandreyshev.geometry.frame

import ru.iandreyshev.geometry.vector.Vec2f

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
