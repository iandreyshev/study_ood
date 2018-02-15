package duck

import behavior.dance.IDanceBehavior
import behavior.fly.IFlyBehavior
import behavior.quack.IQuackBehavior

abstract class Duck(
        private var mFlyBehavior: IFlyBehavior,
        private var mQuackBehavior: IQuackBehavior,
        private var mDanceBehavior: IDanceBehavior) {

    var flyBehavior: IFlyBehavior
        get() = mFlyBehavior
        set(value) {
            mFlyBehavior = value
        }
    var quackBehavior: IQuackBehavior
        get() = mQuackBehavior
        set(value) {
            mQuackBehavior = value
        }
    var danceBehavior: IDanceBehavior
        get() = mDanceBehavior
        set(value) {
            mDanceBehavior = value
        }

    open fun quack() = mQuackBehavior.quack()

    open fun fly() = mFlyBehavior.fly()

    open fun dance() = mDanceBehavior.dance()

    abstract fun display()
}
