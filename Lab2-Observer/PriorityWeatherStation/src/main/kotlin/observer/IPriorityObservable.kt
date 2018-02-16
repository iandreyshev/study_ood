package observer

interface IPriorityObservable<out T> {
    fun registerObserver(observer: IObserver<T>, priority: Int)

    fun removeObserver(observer: IObserver<T>)

    fun notifyObservers()
}
