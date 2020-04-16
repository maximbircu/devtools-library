package com.maximbircu.devtools.configscreens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maximbircu.devtools.R.layout
import com.maximbircu.devtools.android.DevToolsConfigurationScreen
import com.maximbircu.devtools.android.readers.yaml
import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.readers.DevToolsSources
import kotlinx.android.synthetic.main.activity_tools_container.devToolsContainer

class YamlSourceConfigActivity : AppCompatActivity() {
    private lateinit var devtools: DevTools

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, YamlSourceConfigActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_tools_container)

        val source = DevToolsSources.yaml(assets, "dev-tools.yml")
        devtools = DevTools.create(source)

        DevToolsConfigurationScreen.attachToView(devToolsContainer, devtools)
    }
}
