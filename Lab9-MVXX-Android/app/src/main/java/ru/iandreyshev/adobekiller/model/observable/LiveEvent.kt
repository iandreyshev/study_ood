package ru.iandreyshev.adobekiller.model.observable

import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

class LiveEvent<T>(initialValue: T) : ObservableProperty<T>(initialValue) {

    private var mOnChange: ((T) -> Unit)? = null

    fun observe(onChangeAction: (T) -> Unit) {
        mOnChange = onChangeAction
    }

    fun clear() {
        mOnChange = null
    }

    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
        mOnChange?.invoke(newValue)
    }
}
