package shape

interface ICompositeShape : IShape {
    fun add(shape: IShape)

    fun remove(shape: IShape)
}
