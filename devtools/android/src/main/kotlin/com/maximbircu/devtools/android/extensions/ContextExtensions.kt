package com.maximbircu.devtools.android.extensions

import android.content.Context
import android.view.LayoutInflater

internal val Context.inflater: LayoutInflater get() = LayoutInflater.from(this)
