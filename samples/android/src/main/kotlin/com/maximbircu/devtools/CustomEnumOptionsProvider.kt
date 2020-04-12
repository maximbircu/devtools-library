package com.maximbircu.devtools

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maximbircu.devtools.common.presentation.tools.enum.EnumOptionsProvider

class CustomEnumOptionsProvider(private val fileName: String? = null) : EnumOptionsProvider {
    override fun getOptions(): Map<String, String> {
        val config = SampleApplication.application.assets.open(fileName!!).reader()
        return Gson().fromJson(config, object : TypeToken<Map<String, Any>>() {}.type)
    }
}
