package ru.iandreyshev.adobeKiller.presentation.drawing.extension

interface ISimpleIterator<TValue> {
    fun forEach(action: (TValue) -> Unit)
}

fun <TValue> ISimpleIterator<TValue>.forEach2(action: TValue.() -> Unit) {
    forEach { it.apply(action) }
}

fun <TElement, TValue> ISimpleIterator<TElement>.getAllSameOrNull(transform: TElement.() -> TValue): TValue? {
    var isFirstComplete = false
    var first: TValue? = null

    forEach { element ->
        val transElement = transform(element)

        if (!isFirstComplete) {
            isFirstComplete = true
            first = transElement
        }

        if (transElement != first) {
            first = null
            return@forEach
        }
    }

    return first
}

fun <TValue> ISimpleIterator<TValue>.doActionOrFalse(action: (TValue) -> Unit): Boolean {
    var isNotEmpty = false
    forEach {
        isNotEmpty = true
        it.apply(action)
    }
    return isNotEmpty
}
