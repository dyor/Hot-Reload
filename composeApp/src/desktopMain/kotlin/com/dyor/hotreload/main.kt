package com.dyor.hotreload

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Hot Reload",
    ) {
        App()
    }
}