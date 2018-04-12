package draft

import shape.IShape

class PictureDraft : IPictureDraft {
    private val mShapes = HashSet<IShape>(0)

    override val count: Int
        get() = mShapes.size

    override fun insert(shape: IShape): Boolean {
        return mShapes.add(shape)
    }

    override fun forEach(action: (IShape) -> Unit) {
        mShapes.forEach(action)
    }

    override fun reset() = mShapes.clear()
}
