package com.maximbircu.devtools.common

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesProvider {
    private var appContext: Context? = null
    private val application: Context
        get() = appContext ?: initAndGetAppCtxWithReflection().also { appContext = it }

    fun getSharedPreferences(name: String): SharedPreferences {
        return application.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
    private fun initAndGetAppCtxWithReflection(): Context {
        val activityThread = Class.forName("android.app.ActivityThread")
        return activityThread.getDeclaredMethod("currentApplication").invoke(null) as Context
    }
}
