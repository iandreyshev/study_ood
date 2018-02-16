package observer

interface ISubject<out TNewData> {
    val data: TNewData

    val name: String
}
