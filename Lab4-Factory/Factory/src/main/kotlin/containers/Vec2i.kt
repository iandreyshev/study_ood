package containers

class Vec2i(val x: Int, val y: Int) {
    constructor() : this(0, 0)

    override fun toString(): String = "($x, $y)"
}
