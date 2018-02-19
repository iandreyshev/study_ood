package observer

abstract class Observable<out TNewData> : IObservable<TNewData> {

    private val mObservers: HashSet<IObserver<TNewData>> = HashSet()

    override fun registerObserver(observer: IObserver<TNewData>) {
        mObservers.add(observer)
    }

    override fun removeObserver(observer: IObserver<TNewData>) {
        mObservers.remove(observer)
    }

    override fun notifyObservers() {
        val observersToUpdate = mObservers

        observersToUpdate.forEach { it.update(this) }
    }
}
