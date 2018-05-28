package ru.iandreyshev.adobeKiller.domain.presentationModel

import ru.iandreyshev.adobeKiller.domain.command.Command
import ru.iandreyshev.adobeKiller.domain.command.ICommandQueue
import ru.iandreyshev.adobeKiller.domain.command.cmd.*
import ru.iandreyshev.adobeKiller.domain.model.CanvasObject
import ru.iandreyshev.adobeKiller.presentation.drawing.container.Vec2f
import ru.iandreyshev.adobeKiller.presentation.drawing.frame.IFrame
import ru.iandreyshev.adobeKiller.presentation.drawing.style.IStyle

class CanvasObjectModel(
        private val commandQueue: ICommandQueue,
        private val onDataChanged: () -> Unit
) : ICanvasObjectModel {

    override fun notifyDataChanges(canvasObject: CanvasObject, prevFrame: IFrame) {
        val newFrame = canvasObject.frame
        val oldPos = prevFrame.position
        val newPos = newFrame.position

        val isSizeChanged = (prevFrame.width != newFrame.width || prevFrame.height != newFrame.height)
        val isPositionChanged = (oldPos.x != newPos.x || oldPos.y != newPos.y)

        if (isSizeChanged) applyCommand {
            ResizeFrameCommand(
                    frame = canvasObject.frame,
                    oldSize = Vec2f(prevFrame.width, prevFrame.height),
                    newSize = Vec2f(newFrame.width, newFrame.height)
            )
        }

        if (isPositionChanged) applyCommand {
            MoveFrameCommand(
                    frame = canvasObject.frame,
                    oldPosition = oldPos,
                    newPosition = canvasObject.frame.position
            )
        }
    }

    override fun notifyDataChanges(canvasObject: CanvasObject, prevStyle: IStyle) {
        val newStyle = canvasObject.style

        if (newStyle.fillColor != prevStyle.fillColor) applyCommand {
            ChangeFillColorCommand(
                    style = canvasObject.style,
                    oldColor = prevStyle.fillColor,
                    newColor = newStyle.fillColor)
        }

        if (newStyle.strokeColor != prevStyle.strokeColor) applyCommand {
            ChangeStrokeColorCommand(
                    style = canvasObject.style,
                    oldColor = prevStyle.strokeColor,
                    newColor = newStyle.strokeColor
            )
        }

        if (newStyle.strokeSize != prevStyle.strokeSize) applyCommand {
            ResizeStrokeCommand(
                    style = canvasObject.style,
                    oldSize = prevStyle.strokeSize,
                    newSize = newStyle.strokeSize
            )
        }

        canvasObject.resetProperties()
    }

    private fun applyCommand(buildCmdAction: () -> Command) {
        commandQueue.apply(buildCmdAction)
        onDataChanged()
    }

}
