pluginManagement {
    val agp = "7.4.0-alpha08"
    val kotlin = "1.4.20"

    plugins {
        id("com.android.application") version agp
        id("org.jmailen.kotlinter") version "3.3.0"
        id("com.squareup.sqldelight") version "1.4.4"
        id("com.prof18.kmp.fatframework.cocoa") version "0.0.1"
        kotlin("multiplatform") version kotlin
        kotlin("native.cocoapods") version kotlin
        kotlin("android") version kotlin
    }
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.contains("com.android")) {
                useModule("com.android.tools.build:gradle:$agp")
            }
        }
    }
}
rootProject.name = "MyApplication"


include(":androidApp")
include(":shared")

