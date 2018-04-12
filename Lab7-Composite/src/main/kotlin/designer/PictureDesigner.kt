package designer

import draft.IPictureDraft
import shape.IShape
import factory.IShapeFactory

class PictureDesigner(
        private val shapeFactory: IShapeFactory,
        override val draft: IPictureDraft
) : IDesigner {
    override fun addShape(description: List<String>): IShape? {
        val shape = shapeFactory.create(description) ?: return null
        return if (draft.insert(shape)) shape else null
    }

    override fun resetDraft() = draft.reset()
}
