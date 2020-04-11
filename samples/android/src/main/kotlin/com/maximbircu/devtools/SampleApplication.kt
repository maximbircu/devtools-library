package com.maximbircu.devtools

import android.app.Application

class SampleApplication : Application() {
    companion object {
        lateinit var application: SampleApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}
