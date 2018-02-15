package duck

internal fun doNothing() = {}

internal fun flyWithWings(): () -> Unit {
    var flightCount: Long = 0

    return fun() {
        ++flightCount
        println("I`m flying with wings. Flight count is $flightCount")
    }
}

internal fun loudQuack() = {
    println("QUACK!!!")
}

internal fun danceWaltz() = {
    println("I`m dancing waltz!")
}

internal fun goToTheBottom() = {
    println("I`m drowninig!")
}

internal fun swimLikeDuck() = {
    println("I`m swimming like a duck")
}
