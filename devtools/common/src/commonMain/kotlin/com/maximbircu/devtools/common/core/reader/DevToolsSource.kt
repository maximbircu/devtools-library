package com.maximbircu.devtools.common.core.reader

/**
 * Helps to differentiate the dev tools sources and serves as a [DevToolsReader] factory.
 *
 * The library takes a collection of [DevToolsSource], and gathers dev tools objects using readers.
 */
interface DevToolsSource {
    /**
     * @return [DevToolsReader] the reader able to parse the source and extract dev tool objects.
     */
    fun getReader(): DevToolsReader
}
