package observer

interface IObservable<out TNewData> {
    fun registerObserver(observer: IObserver<TNewData>)

    fun removeObserver(observer: IObserver<TNewData>)

    fun notifyObservers()
}
