package observer

interface IObserver<in TNewData> {
    fun update(subject: IObservable<TNewData>)
}
