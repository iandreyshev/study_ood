package canvas

import containers.Vec2i
import java.awt.BasicStroke
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JFrame
import javax.swing.JPanel

class WindowedCanvas(width: Int, height: Int) : JFrame("Painter"), ICanvas {
    init {
        setSize(width, height)
        isResizable = false
        isVisible = true
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }

    override var penColor: Color = Color.BLACK
    override var stroke: Float = 10f

    override fun paint(g: Graphics?) {
        g?.color = java.awt.Color.RED
        super.paint(g)
    }

    override fun drawLine(from: Vec2i, to: Vec2i) = draw { g ->
        g.drawLine(from.x, from.y, to.x, to.y)
    }

    override fun drawEllipse(center: Vec2i, horizontalRadius: Int, verticalRadius: Int) = draw { g ->
        g.drawOval(center.x, center.y, horizontalRadius, verticalRadius)
    }

    private fun draw(drawAction: (Graphics) -> Unit) {
        contentPane.add(object : JPanel() {
            override fun paintComponent(g: Graphics?) {
                (g as Graphics2D).stroke = BasicStroke(stroke)
                g.color = when (penColor) {
                    Color.RED -> java.awt.Color.RED
                    Color.GREEN -> java.awt.Color.GREEN
                    Color.BLUE -> java.awt.Color.BLUE
                    Color.PINK -> java.awt.Color.PINK
                    Color.YELLOW -> java.awt.Color.YELLOW
                    Color.BLACK -> java.awt.Color.BLACK
                }
                drawAction(g)
            }
        })
        isVisible = true
    }
}
