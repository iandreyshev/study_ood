package ru.iandreyshev.gumballmachine.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsLogger(context: Context) : IAnalyticsLogger {
    companion object {
        private const val FILL_ITEM_ID = "fill_button"
    }

    private val mInstance = FirebaseAnalytics.getInstance(context)

    override fun onStartFillMachine() =
            logWrap(FirebaseAnalytics.Event.SELECT_CONTENT) {
                putString(FirebaseAnalytics.Param.ITEM_ID, FILL_ITEM_ID)
                putString(FirebaseAnalytics.Param.CONTENT_TYPE, "button")
            }

    // TODO: Create @FirebaseEvent annotation
    private fun logWrap(eventType: String, bundleAction: Bundle.() -> Unit) {
        val bundle = Bundle()
        bundleAction(bundle)
        mInstance.logEvent(eventType, bundle)
    }
}
