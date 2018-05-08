package ru.iandreyshev.adobekiller.app

import android.annotation.SuppressLint
import android.app.Application

class AdobeKillerApp : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
