package duck

import behavior.dance.DoNotDance
import behavior.fly.FlyNoWay
import behavior.quack.MuteQuackBehavior

class DecoyDuck : Duck(FlyNoWay(), MuteQuackBehavior(), DoNotDance()) {
    override fun display() = println("I'm decoy duck")
}
