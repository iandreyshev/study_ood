package duck

import behavior.dance.DoNotDance
import behavior.fly.FlyNoWay
import behavior.quack.QuackBehavior

class ModelDuck : Duck(FlyNoWay(), QuackBehavior(), DoNotDance()) {
    override fun display() = println("I'm model duck")
}
