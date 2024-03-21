plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation("dev.icerock.moko:mvvm-core:0.16.1") // only ViewModel, EventsDispatcher, Dispatchers.UI
            implementation("dev.icerock.moko:mvvm-flow:0.16.1") // api mvvm-core, CFlow for native and binding extensions
            implementation("dev.icerock.moko:mvvm-livedata:0.16.1") // api mvvm-core, LiveData and extensions
            implementation("dev.icerock.moko:mvvm-state:0.16.1") // api mvvm-livedata, ResourceState class and extensions
            implementation("dev.icerock.moko:mvvm-livedata-resources:0.16.1") // api mvvm-core, moko-resources, extensions for LiveData with moko-resources
            implementation("dev.icerock.moko:mvvm-flow-resources:0.16.1") // api mvvm-core, moko-resources, extensions for Flow with moko-resources
            implementation("dev.icerock.moko:mvvm-compose:0.16.1") // api mvvm-core, getViewModel for Compose Multiplatform
            implementation("dev.icerock.moko:mvvm-flow-compose:0.16.1") // api mvvm-flow, binding extensions for Compose Multiplatform
            implementation("dev.icerock.moko:mvvm-livedata-compose:0.16.1") // api mvvm-livedata, binding extensions for Compose Multiplatform

        }
        androidMain.dependencies {
            implementation("dev.icerock.moko:mvvm-livedata-material:0.16.1") // api mvvm-livedata, Material library android extensions
            implementation("dev.icerock.moko:mvvm-livedata-glide:0.16.1") // api mvvm-livedata, Glide library android extensions
            implementation("dev.icerock.moko:mvvm-livedata-swiperefresh:0.16.1") // api mvvm-livedata, SwipeRefreshLayout library android extensions
            implementation("dev.icerock.moko:mvvm-databinding:0.16.1") // api mvvm-livedata, DataBinding support for Android
            implementation("dev.icerock.moko:mvvm-viewbinding:0.16.1") // api mvvm-livedata, ViewBinding support for Android
        }
        iosMain.dependencies {
            // iOS 用の依存関係定義
            implementation("dev.icerock.moko:mvvm-core:0.16.1")
            implementation("dev.icerock.moko:mvvm-livedata:0.16.1")
            implementation("dev.icerock.moko:mvvm-livedata-resources:0.16.1")
            implementation("dev.icerock.moko:mvvm-state:0.16.1")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation("dev.icerock.moko:mvvm-test:0.16.1") // test utilities
        }
    }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export("dev.icerock.moko:mvvm-core")
            export("dev.icerock.moko:mvvm-livedata")
            export("dev.icerock.moko:mvvm-livedata-resources")
            export("dev.icerock.moko:mvvm-state")
        }
    }

    task("testClasses")
}

android {
    namespace = "com.example.android_ble_walk01"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
