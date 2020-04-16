package com.maximbircu.devtools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maximbircu.devtools.configscreens.CombinedSourcesConfigActivity
import com.maximbircu.devtools.configscreens.JsonSchemaSourceConfigActivity
import com.maximbircu.devtools.configscreens.MemorySourceConfigActivity
import com.maximbircu.devtools.configscreens.YamlSourceConfigActivity
import kotlinx.android.synthetic.main.activity_main.combinedSourceTools
import kotlinx.android.synthetic.main.activity_main.jsonSchemaSourceTools
import kotlinx.android.synthetic.main.activity_main.memorySourceTools
import kotlinx.android.synthetic.main.activity_main.yamlSourceTools

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memorySourceTools.setOnClickListener { MemorySourceConfigActivity.start(this) }
        yamlSourceTools.setOnClickListener { YamlSourceConfigActivity.start(this) }
        jsonSchemaSourceTools.setOnClickListener { JsonSchemaSourceConfigActivity.start(this) }
        combinedSourceTools.setOnClickListener { CombinedSourcesConfigActivity.start(this) }
    }
}
