package display

import com.sun.javafx.geom.Vec2d
import info.WeatherInfo
import observer.IObserver
import kotlin.math.round

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
        private val mTotalDir: Vec2d = Vec2d(.0, .0)
        private val mTotalDirInRadians
            get() = Math.atan2(mTotalDir.y, mTotalDir.x)

        fun calc(directionAngle: Double) {
            mTotalDir.x += Math.cos(Math.toRadians(directionAngle))
            mTotalDir.y += Math.sin(Math.toRadians(directionAngle))
        }

        override fun toString(): String {
            return round(Math.toDegrees(mTotalDirInRadians)).toString()
        }
    }
}
