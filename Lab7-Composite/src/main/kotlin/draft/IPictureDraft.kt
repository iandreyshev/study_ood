package draft

import shape.IShape

interface IPictureDraft {
    val count: Int

    fun insert(shape: IShape): Boolean

    fun forEach(action: (IShape) -> Unit)

    fun reset()
}
