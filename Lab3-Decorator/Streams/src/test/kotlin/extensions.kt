import java.util.*

val String.pathToTxt: String
    get() = "src/test/resources/$this.txt"

fun randByte(key: Int = Calendar.getInstance().timeInMillis.toInt()): Byte {
    return Random().nextInt(key).toByte()
}

fun randLong(key: Int = Calendar.getInstance().timeInMillis.toInt()): Long {
    return Random().nextInt(key).toLong()
}
