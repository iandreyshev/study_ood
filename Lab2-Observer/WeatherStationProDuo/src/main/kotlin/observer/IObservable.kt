package observer

interface IObservable<out TData> {
    val data: TData

    fun registerObserver(observer: IObserver<TData>)

    fun removeObserver(observer: IObserver<TData>)

    fun notifyObservers()
}
