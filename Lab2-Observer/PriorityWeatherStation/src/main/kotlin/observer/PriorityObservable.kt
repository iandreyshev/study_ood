package observer

import java.util.*
import kotlin.Comparator

abstract class PriorityObservable<out T>(
        comparator: Comparator<Int> = Comparator.naturalOrder())
    : IPriorityObservable<T> {

    protected abstract val data: T
    private val mObservers: TreeSet<Pair<IObserver<T>, Int>> = TreeSet(
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
        val observersToUpdate = mObservers

        observersToUpdate.forEach { it.first.update(newData) }
    }
}
