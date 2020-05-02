package com.maximbircu.devtools

import android.app.Application
import com.maximbircu.devtools.MemoryDevToolsProvider.tools
import com.maximbircu.devtools.android.DevToolsViewRegistry
import com.maximbircu.devtools.android.readers.json
import com.maximbircu.devtools.android.readers.yaml
import com.maximbircu.devtools.common.DevTools
import com.maximbircu.devtools.common.readers.DevToolsSources
import com.maximbircu.devtools.common.readers.memory
import com.maximbircu.devtools.customtool.MyCustomTool
import com.maximbircu.devtools.customtool.MyCustomToolLayout

class SampleApplication : Application() {
    lateinit var memoryDevTools: DevTools private set
    lateinit var yamlDevTools: DevTools private set
    lateinit var jsonDevTools: DevTools private set
    lateinit var combinedDevTools: DevTools private set

    companion object {
        lateinit var application: SampleApplication
            private set
    }

    @Suppress("LongMethod")
    override fun onCreate() {
        super.onCreate()
        application = this

        // Register MyCustomTool
        DevToolsViewRegistry.register(MyCustomTool::class) { MyCustomToolLayout(it) }

        val memorySource = DevToolsSources.memory(tools)
        val ymlSource = DevToolsSources.yaml(assets, "dev-tools.yml")
        val jsonSource = DevToolsSources.json(assets, "dev-tools.json")
        val customSource = MyCustomDevToolsSource()

        memoryDevTools = DevTools.create("MEMORY", memorySource, customSource)
        yamlDevTools = DevTools.create("YAML", ymlSource, customSource)
        jsonDevTools = DevTools.create("JSON", jsonSource, customSource)
        combinedDevTools = DevTools.create(
            "COMBINED",
            memorySource,
            ymlSource,
            jsonSource,
            customSource
        )
    }
}
