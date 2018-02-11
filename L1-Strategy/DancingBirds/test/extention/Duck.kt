package extention

import duck.Duck

internal fun Duck.play() {
    this.display()
    this.fly()
    this.quack()
    this.dance()
    println("---")
}
