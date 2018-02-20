import java.util.*

val ClosedRange<Int>.random: Int
    get() {
        val bound = if (endInclusive - start > 0) endInclusive - start else 1
        return Random().nextInt(bound) + start
    }

