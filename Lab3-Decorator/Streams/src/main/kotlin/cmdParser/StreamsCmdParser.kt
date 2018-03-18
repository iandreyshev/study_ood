package cmdParser

import org.apache.commons.cli.*

class StreamsCmdParser(
        private val mOnFilesFound: (String, String) -> Unit,
        private val mOnCompress: () -> Unit,
        private val mOnDecompress: () -> Unit,
        private val mOnEncrypt: (Long) -> Unit,
        private val mOnDecrypt: (Long) -> Unit
) : CmdParser() {

    companion object {
        private const val HELP_TITLE = "streams.exe"
        private const val OPT_HELP = "help"
        private const val OPT_ENCRYPT = "encrypt"
        private const val OPT_DECRYPT = "decrypt"
        private const val OPT_COMPRESS = "compress"
        private const val OPT_DECOMPRESS = "decompress"
        private const val OPT_FILES = "files"
    }

    override fun onParseSuccess(cmd: CommandLine) {
        val files = cmd.getOptionValues(OPT_FILES)
        mOnFilesFound(files[0], files[1])

        cmd.options.forEach { option ->
            fun doIfOption(optionName: String, event: () -> Unit) {
                if (optionName == option.opt) {
                    event()
                }
            }

            try {
                doIfOption(OPT_HELP, { printHelp() })
                doIfOption(OPT_ENCRYPT, { mOnEncrypt(option.value.toLong()) })
                doIfOption(OPT_DECRYPT, { mOnDecrypt(option.value.toLong()) })
                doIfOption(OPT_COMPRESS, mOnCompress)
                doIfOption(OPT_DECOMPRESS, mOnDecompress)
            } catch (ex: Exception) {
                println("Invalid args format for option '${option.opt}'")
            }
        }
    }

    override fun onParseError(ex: Exception) {
        println(ex.message)
        printHelp()
    }

    override val options: Options
        get() {
            val options = Options()

            options.addOption(helpOption)
            options.addOption(filesOption)
            options.addOptionGroup(compressGroup)
            options.addOptionGroup(cryptGroup)

            return options
        }

    private val helpOption: Option
        get() = Option(OPT_HELP, "Print help")

    private val cryptGroup: OptionGroup
        get() {
            val encrypt = Option(OPT_ENCRYPT, true, "Encrypt bytes")
            encrypt.args = 1
            val decompress = Option(OPT_DECOMPRESS, false, "Decompress bytes by RLE algorithm")

            val group = OptionGroup()
            group.addOption(encrypt)
            group.addOption(decompress)

            return group
        }

    private val compressGroup: OptionGroup
        get() {
            val decrypt = Option(OPT_DECRYPT, true, "Decrypt bytes")
            decrypt.args = 1
            val compress = Option(OPT_COMPRESS, false, "Compress bytes by RLE algorithm")

            val group = OptionGroup()
            group.addOption(decrypt)
            group.addOption(compress)

            return group
        }

    private val filesOption: Option
        get() {
            val option = Option(OPT_FILES, true, "Files to work")
            option.args = 2
            option.isRequired = true

            return option
        }

    private fun printHelp() {
        val formatter = HelpFormatter()
        formatter.printHelp(HELP_TITLE, options)
    }
}
