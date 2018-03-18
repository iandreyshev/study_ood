import canvas.WindowedCanvas
import client.designer.PictureDesigner
import client.draft.PictureDraft
import client.painter.SimplePainter
import shape.factory.ShapeFactory

class Main {
    companion object {
        private val WHITESPACES = Regex("[\r\n\t ]+")

        private val mDesigner = PictureDesigner(ShapeFactory(), PictureDraft())
        private val mPainter = SimplePainter()
        private val mCanvas = WindowedCanvas(720, 480)

        @JvmStatic
        fun main(args: Array<String>) {
            inputLoop@ while (true) {
                val command = readLine()
                        ?.toLowerCase()
                        ?.trim()

                when (command) {
                    null -> {
                        println("Input error")
                        break@inputLoop
                    }
                    "exit" -> break@inputLoop
                    "draw" -> draw()
                    else -> tryAddShape(command)
                }
            }

            mCanvas.dispose()
            println("Good bye!")
        }

        private fun draw() {
            val draft = mDesigner.draft

            if (draft.count == 0) {
                println("Draft is empty. Nothing to draw.")
            } else {
                mPainter.draw(draft, mCanvas)
                draft.forEach { shape ->
                    println("Draw [${shape.description}]")
                }
                mDesigner.resetDraft()
            }
        }

        private fun tryAddShape(description: String) {
            val shapeParams = description.split(WHITESPACES)
            val shape = mDesigner.addShape(shapeParams)

            return if (shape == null) {
                println("Invalid shape description '$description'")
            } else {
                println("Shape [${shape.description}] added to draft")
            }
        }
    }
}
