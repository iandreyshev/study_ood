package ru.iandreyshev.adobeKiller.presentation.ui.dialog

import android.content.Context
import android.support.annotation.StringRes
import kotlinx.android.synthetic.main.view_action_dialog.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import ru.iandreyshev.adobeKiller.R
import ru.iandreyshev.adobeKiller.presentation.ui.extension.inflate

class ActionDialog(
        private val context: Context,
        @StringRes private val titleResId: Int,
        private val onSubmit: String.() -> Unit) {

    init {
        val view = context.inflate(R.layout.view_action_dialog)
        context.alert {
            title = context.resources.getString(titleResId)
            customView = view
            okButton {
                onSubmit(view.etActionArgs.text.toString())
            }
        }.show()
    }

}
