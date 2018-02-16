package observer

abstract class Observable<out T> : IObservable<T> {

    protected abstract val data: T

    private val mObservers: HashSet<IObserver<T>> = HashSet()

    override fun registerObserver(observer: IObserver<T>) {
        mObservers.add(observer)
    }

    override fun removeObserver(observer: IObserver<T>) {
        mObservers.remove(observer)
    }

    override fun notifyObservers() {
        val newData = data
        val observersToUpdate = mObservers

        observersToUpdate.forEach { it.update(newData) }
    }
}
