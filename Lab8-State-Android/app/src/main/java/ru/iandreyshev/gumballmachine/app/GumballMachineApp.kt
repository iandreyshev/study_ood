package ru.iandreyshev.gumballmachine.app

import android.annotation.SuppressLint
import android.app.Application

class GumballMachineApp : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
