package duck

typealias FlyBehavior = () -> Unit
typealias QuackBehavior = () -> Unit
typealias DanceBehavior = () -> Unit

abstract class Duck(
        private var mFlyBehavior: FlyBehavior,
        private var mQuackBehavior: QuackBehavior,
        private var mDanceBehavior: DanceBehavior) {

    var flyBehavior: FlyBehavior
        get() = mFlyBehavior
        set(value) {
            mFlyBehavior = value
        }
    var quackBehavior: QuackBehavior
        get() = mQuackBehavior
        set(value) {
            mQuackBehavior = value
        }
    var danceBehavior: DanceBehavior
        get() = mDanceBehavior
        set(value) {
            mDanceBehavior = value
        }

    open fun quack() = mQuackBehavior()

    open fun fly() = mFlyBehavior()

    open fun dance() = mDanceBehavior()

    abstract fun display()
}

