package com.maximbircu.devtools.configscreens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maximbircu.devtools.R.layout
import com.maximbircu.devtools.android.DevToolsConfigurationScreen
import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.readers.DevToolsSources
import com.maximbircu.devtools.common.readers.json
import kotlinx.android.synthetic.main.activity_tools_container.devToolsContainer

class JsonSchemaSourceConfigActivity : AppCompatActivity() {
    private lateinit var devtools: DevTools

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, JsonSchemaSourceConfigActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_tools_container)

        val source = DevToolsSources.json(assets.open("dev-tools.json").reader().readText())
        devtools = DevTools.create(source)

        DevToolsConfigurationScreen.attachToView(devToolsContainer, devtools)
    }
}
