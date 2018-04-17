package containers

class Vec2i(val x: Int, val y: Int) {
    constructor() : this(0, 0)

    operator fun minus(value: Vec2i): Vec2i =
            Vec2i(x - value.x, y - value.y)

    override fun toString(): String = "($x, $y)"
}
