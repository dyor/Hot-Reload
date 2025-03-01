package com.dyor.hotreload

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform