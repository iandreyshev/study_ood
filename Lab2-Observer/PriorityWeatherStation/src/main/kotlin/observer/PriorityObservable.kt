package observer

import java.util.*

abstract class PriorityObservable<out T>(
        comparator: Comparator<Int> = Comparator.naturalOrder())
    : IPriorityObservable<T> {

    protected abstract val data: T
    private val mObservers: PriorityQueue<Pair<IObserver<T>, Int>> = PriorityQueue(
            Comparator { o1, o2 -> comparator.compare(o1.second, o2.second) }
    )

    override fun registerObserver(observer: IObserver<T>, priority: Int) {
        mObservers.add(Pair(observer, priority))
    }

    override fun removeObserver(observer: IObserver<T>) {
        mObservers.removeIf {
            it.first == observer
        }
    }

    override fun notifyObservers() {
        val newData = data
        mObservers.forEach { it.first.update(newData) }
    }
}
