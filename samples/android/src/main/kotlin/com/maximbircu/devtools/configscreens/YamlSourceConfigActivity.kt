package com.maximbircu.devtools.configscreens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maximbircu.devtools.SampleApplication
import com.maximbircu.devtools.android.DevToolsConfigurationScreen
import com.maximbircu.devtools.databinding.ActivityToolsContainerBinding

class YamlSourceConfigActivity : AppCompatActivity() {
    private val devtools = SampleApplication.application.yamlDevTools

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, YamlSourceConfigActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityToolsContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        devtools.onConfigUpdated = { isCriticalUpdate ->
            val toast = Toast.makeText(
                this,
                "A ${if (isCriticalUpdate) "critical" else "non-critical"} updated performed!",
                Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }



        DevToolsConfigurationScreen.attachToView(
            binding.devToolsContainer,
            devtools
        ) {
            finish()
        }
    }
}
