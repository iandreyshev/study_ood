package extention

import duck.Duck

fun Duck.play() {
    this.display()
    this.fly()
    this.quack()
    this.dance()
    this.swim()
    println("---")
}
