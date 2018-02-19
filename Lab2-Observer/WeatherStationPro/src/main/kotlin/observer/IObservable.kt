package observer

interface IObservable<out T> {
    fun registerObserver(observer: IObserver<T>)

    fun removeObserver(observer: IObserver<T>)

    fun notifyObservers()
}
