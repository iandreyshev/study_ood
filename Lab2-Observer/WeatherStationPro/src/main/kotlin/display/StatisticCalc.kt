package display

open class StatisticCalc(private val mName: String = "Value") {
    private var mMinValue: Double = Double.MIN_VALUE
    private var mMaxValue: Double = Double.MAX_VALUE
    private var mAccValue: Double = .0
    private var mCountAcc: Long = 0L

    fun calc(newValue: Double) {
        mMinValue = Math.min(mMinValue, newValue)
        mMaxValue = Math.max(mMaxValue, newValue)
        mAccValue += newValue
        ++mCountAcc
    }

    override fun toString(): String {
        return """
            Min $mName $mMinValue
            Max $mName $mMaxValue
            Average $mName ${mAccValue / mCountAcc}"""
    }
}
