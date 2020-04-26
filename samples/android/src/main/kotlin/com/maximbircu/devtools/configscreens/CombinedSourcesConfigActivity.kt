package com.maximbircu.devtools.configscreens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maximbircu.devtools.R.layout
import com.maximbircu.devtools.SampleApplication
import com.maximbircu.devtools.android.DevToolsConfigurationScreen
import com.maximbircu.devtools.common.DevTools
import kotlinx.android.synthetic.main.activity_tools_container.devToolsContainer

class CombinedSourcesConfigActivity : AppCompatActivity() {
    private val devtools: DevTools = SampleApplication.application.combinedDevTools

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CombinedSourcesConfigActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_tools_container)

        devtools.onConfigUpdated = { isCriticalUpdate ->
            val toast = Toast.makeText(
                this,
                "A ${if (isCriticalUpdate) "critical" else "non-critical"} updated performed!",
                Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }

        DevToolsConfigurationScreen.attachToView(devToolsContainer, devtools)
    }
}
