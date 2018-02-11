package duck

import behavior.dance.DanceWaltz
import behavior.fly.FlyWithWings
import behavior.quack.QuackBehavior

class MallardDuck : Duck(FlyWithWings(), QuackBehavior(), DanceWaltz()) {
    override fun display() = println("I'm mallard duck")
}
