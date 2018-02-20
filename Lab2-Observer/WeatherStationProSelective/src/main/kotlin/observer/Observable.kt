package observer

typealias ObserversMap<T> = HashMap<NotifyPredicate<T>, HashSet<IObserver<T>>>

abstract class Observable<out TData> : IObservable<TData> {

    protected abstract val data: TData

    private var mLatestData: TData? = null

    private val mObservers: ObserversMap<TData> = HashMap()

    override fun registerObserver(notifyPredicate: NotifyPredicate<TData>, observer: IObserver<TData>) {
        val eventObservers = mObservers[notifyPredicate]

        if (eventObservers != null) {
            eventObservers.add(observer)
        } else {
            mObservers[notifyPredicate] = HashSet(arrayListOf(observer))
        }
    }

    override fun removeObserver(notifyPredicate: NotifyPredicate<TData>, observer: IObserver<TData>) {
        val observers = mObservers[notifyPredicate]
        observers?.remove(observer)

        if (observers?.isEmpty() == true) {
            mObservers.remove(notifyPredicate)
        }
    }

    override fun notifyObservers() {
        HashMap(mObservers).forEach { observersByPredicate ->
            if (observersByPredicate.key(mLatestData, data)) {

                observersByPredicate.value.forEach { observer ->
                    observer.update(data)
                }
            }
        }

        mLatestData = data
    }
}
