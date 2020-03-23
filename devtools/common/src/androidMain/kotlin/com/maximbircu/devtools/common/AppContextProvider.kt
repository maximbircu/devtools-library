package com.maximbircu.devtools.common

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
private var appContext: Context? = null

val application: Context get() = appContext ?: initAndGetAppCtxWithReflection()


@SuppressLint("PrivateApi", "DiscouragedPrivateApi")
private fun initAndGetAppCtxWithReflection(): Context {
    // Fallback, should only run once per non default process.
    val activityThread = Class.forName("android.app.ActivityThread")
    val ctx = activityThread.getDeclaredMethod("currentApplication").invoke(null) as Context
    appContext = ctx
    return ctx
}
