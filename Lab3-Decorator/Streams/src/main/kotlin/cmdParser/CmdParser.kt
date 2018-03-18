package cmdParser

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Options
import org.apache.commons.cli.PosixParser

abstract class CmdParser {

    private val mParser = PosixParser()

    fun execute(cmd: Array<String>): Boolean {
        return try {
            onParseSuccess(mParser.parse(options, cmd))
            true
        } catch (ex: Exception) {
            onParseError(ex)
            false
        }
    }

    protected abstract fun onParseSuccess(cmd: CommandLine)

    protected abstract fun onParseError(ex: Exception)

    protected abstract val options: Options
}
