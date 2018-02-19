package display

import com.sun.javafx.geom.Vec2d
import info.WeatherInfo
import observer.IObserver

class WindDisplay : IObserver<WeatherInfo> {

    private val mSpeedCalc = StatisticCalc("wind speed")
    private val mWindDirCalc = WindDirectionStatisticCalc()

    override fun update(data: WeatherInfo) {
        mSpeedCalc.calc(data.windSpeed)
        mWindDirCalc.calc(data.windDirectionAngle)

        println("""
            $mSpeedCalc
            $mWindDirCalc
            ----------------""".trimIndent())
    }

    class WindDirectionStatisticCalc {

        private val mTotalDirection: Vec2d = Vec2d(0.0, -1.0)

        fun calc(directionAngle: Double) {
            mTotalDirection.x += Math.cos(directionAngle)
            mTotalDirection.y += Math.sin(directionAngle)

            println(mTotalDirection.toString())

            mTotalDirection.normalize()
        }

        override fun toString(): String = mTotalDirection.toString()
    }
}
