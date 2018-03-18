package client.designer

import client.draft.IPictureDraft
import shape.IShape

interface IDesigner {
    val draft: IPictureDraft

    fun addShape(description: List<String>): IShape?

    fun resetDraft()
}
