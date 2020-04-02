package com.maximbircu.devtools.common.presentation.tools.text

import com.maximbircu.devtools.common.core.mvp.BaseView
import kotlin.reflect.KClass

interface TextToolView : BaseView {
    fun setHint(hint: String?)
    fun setTextValue(value: String?)
    fun setInputDataType(dataType: KClass<*>)
}
