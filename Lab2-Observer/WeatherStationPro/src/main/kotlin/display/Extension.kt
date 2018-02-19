package display

import com.sun.javafx.geom.Vec2d
import kotlin.math.sqrt

fun Vec2d.normalize() {
    val length = 1.0 / sqrt(x * x + y * y)

    x = if (!length.isFinite()) .0 else x * length
    y = if (!length.isFinite()) .0 else y * length
}

fun Vec2d.toString(): String {
    return x.toString() + " " + y.toString()
}
