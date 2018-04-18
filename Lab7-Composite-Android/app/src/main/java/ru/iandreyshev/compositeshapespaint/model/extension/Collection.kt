package extension

fun <TElement, TValue> Collection<TElement>.getAllSameOrNull(transform: TElement.() -> TValue): TValue? {
    val first = transform(firstOrNull() ?: return null)

    forEach { element ->
        if (transform(element) != first) {
            return null
        }
    }

    return first
}

fun <TElement> Collection<TElement>.forEach2(action: TElement.() -> Unit) {
    forEach { action.invoke(it) }
}
