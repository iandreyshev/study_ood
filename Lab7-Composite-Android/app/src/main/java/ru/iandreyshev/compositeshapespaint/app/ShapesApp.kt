package ru.iandreyshev.compositeshapespaint.app

import android.annotation.SuppressLint
import android.app.Application

class ShapesApp : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
