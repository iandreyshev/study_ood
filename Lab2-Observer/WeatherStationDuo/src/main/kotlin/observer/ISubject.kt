package observer

interface ISubject<out TNewData> {
    val data: TNewData
}
