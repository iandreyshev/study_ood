package containers

data class Vec2i(
        val x: Int = 0,
        val y: Int = 0) {

    operator fun minus(value: Vec2i): Vec2i =
            Vec2i(x - value.x, y - value.y)

    override fun toString(): String = "($x, $y)"
}
