package behavior.fly

class FlyWithWings(private var mFlightsCount: Long = 0) : IFlyBehavior {
    override fun fly() {
        mFlightsCount++
        println("I'm flying with wings. Fights count is $mFlightsCount")
    }
}
