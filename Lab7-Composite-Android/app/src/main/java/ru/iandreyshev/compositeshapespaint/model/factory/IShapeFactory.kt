package factory

import ru.iandreyshev.compositeshapespaint.model.shape.IShape

interface IShapeFactory {
    fun create(description: List<String>): IShape?
}
