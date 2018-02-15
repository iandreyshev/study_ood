package duck

import behavior.dance.DoNotDance
import behavior.fly.FlyNoWay
import behavior.quack.SqueakBehavior

class RubberDuck : Duck(FlyNoWay(), SqueakBehavior(), DoNotDance()) {
    override fun display() = println("I'm rubber duck")
}
