package com.example.android_ble_walk01

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform