import com.sun.javafx.geom.Vec2d
import java.util.*
import kotlin.math.sqrt

val ClosedRange<Int>.random: Int
    get() {
        val bound = if (endInclusive - start > 0) endInclusive - start else 1
        return Random().nextInt(bound) + start
    }

