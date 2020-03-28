package com.maximbircu.devtools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.memorySourceTools
import kotlinx.android.synthetic.main.activity_main.ymlSourceTools

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memorySourceTools.setOnClickListener { MemoryToolsActivity.start(this) }
        ymlSourceTools.setOnClickListener { YmlToolsActivity.start(this) }
    }
}
