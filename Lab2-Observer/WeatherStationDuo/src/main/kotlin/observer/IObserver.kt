package observer

interface IObserver<in TNewData> {
    fun update(subject: ISubject<TNewData>)
}
