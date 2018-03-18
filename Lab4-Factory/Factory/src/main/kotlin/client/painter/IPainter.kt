package client.painter

import canvas.ICanvas
import client.draft.IPictureDraft

interface IPainter {
    fun draw(draft: IPictureDraft, canvas: ICanvas)
}
