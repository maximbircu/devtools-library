package com.maximbircu.devtools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maximbircu.devtools.android.extensions.updateFromBundle
import com.maximbircu.devtools.configscreens.CombinedSourcesConfigActivity
import com.maximbircu.devtools.configscreens.JsonSchemaSourceConfigActivity
import com.maximbircu.devtools.configscreens.MemorySourceConfigActivity
import com.maximbircu.devtools.configscreens.YamlSourceConfigActivity
import com.maximbircu.devtools.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.memorySourceTools.setOnClickListener {
            MemorySourceConfigActivity.start(this)
        }
        binding.yamlSourceTools.setOnClickListener {
            YamlSourceConfigActivity.start(this)
        }
        binding.jsonSchemaSourceTools.setOnClickListener {
            JsonSchemaSourceConfigActivity.start(this)
        }
        binding.combinedSourceTools.setOnClickListener {
            CombinedSourcesConfigActivity.start(this)
        }

        SampleApplication.application.memoryDevTools.updateFromBundle(intent.extras)
        SampleApplication.application.yamlDevTools.updateFromBundle(intent.extras)
        SampleApplication.application.jsonDevTools.updateFromBundle(intent.extras)
        SampleApplication.application.combinedDevTools.updateFromBundle(intent.extras)
    }
}
