package factory

import shape.IShape

interface IShapeFactory {
    fun create(description: List<String>): IShape?
}
