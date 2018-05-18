package ru.iandreyshev.adobeKiller.presentation.ui.interfaces

import com.xw.repo.BubbleSeekBar

interface ISeekBarEmptyListener : BubbleSeekBar.OnProgressChangedListener {
    override fun onProgressChanged(bubbleSeekBar: BubbleSeekBar?, progress: Int, progressFloat: Float, fromUser: Boolean) {
    }

    override fun getProgressOnActionUp(bubbleSeekBar: BubbleSeekBar?, progress: Int, progressFloat: Float) {
    }

    override fun getProgressOnFinally(bubbleSeekBar: BubbleSeekBar?, progress: Int, progressFloat: Float, fromUser: Boolean) {
    }
}
