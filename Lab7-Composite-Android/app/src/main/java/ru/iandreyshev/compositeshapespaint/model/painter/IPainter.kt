package painter

import canvas.ICanvas
import draft.IPictureDraft

interface IPainter {
    fun draw(draft: IPictureDraft, canvas: ICanvas)
}
