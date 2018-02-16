package observer

interface IObserver<in T> {
    fun update(data: T)
}
