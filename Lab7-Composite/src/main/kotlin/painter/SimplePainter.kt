package painter

import canvas.ICanvas
import draft.IPictureDraft

class SimplePainter : IPainter {
    override fun draw(draft: IPictureDraft, canvas: ICanvas) {
        draft.forEach { shape ->
            shape.draw(canvas)
        }
    }
}
