package condiment.extension

val String.ucFirst: String
    get() = if (isEmpty()) {
        this
    } else {
        val lower = toLowerCase()
        lower.replaceFirst(lower[0], lower[0].toUpperCase())
    }
