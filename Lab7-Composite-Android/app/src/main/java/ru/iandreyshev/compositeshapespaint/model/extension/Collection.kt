package ru.iandreyshev.compositeshapespaint.model.extension

fun <TElement> Collection<TElement>.forEach2(action: TElement.() -> Unit) {
    forEach { action.invoke(it) }
}
