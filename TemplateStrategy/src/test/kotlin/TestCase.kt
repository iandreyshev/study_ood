import org.junit.Before
import org.junit.Test

class TestCase {
    @Before
    fun setup() {
        println("--- Let`s prepare some tea ---")
    }

    @Test
    fun makeJavaTea() {
        val tea = TeaBeverage()
        tea.asJavaTea()
        tea.prepare()
    }

    @Test
    fun makeGitTea() {
        val tea = TeaBeverage()
        tea.asGitPrincessTea()
        tea.prepare()
    }

    @Test
    fun makeCustomTea() {
        val tea = TeaBeverage()
        tea.onBrew { println("Add some garbage in bag") }
        tea.onAddCondiment { println("Add some sauce") }
        tea.prepare()

    }
}
