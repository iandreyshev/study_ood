package observer

typealias NotifyPredicate<T> = (T?, T) -> Boolean

interface IObservable<out TData> {
    fun registerObserver(notifyPredicate: NotifyPredicate<TData>, observer: IObserver<TData>)

    fun removeObserver(notifyPredicate: NotifyPredicate<TData>, observer: IObserver<TData>)

    fun notifyObservers()
}
