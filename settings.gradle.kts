pluginManagement {
    plugins {
        id("com.android.application") version "7.0.0-alpha09"
        id("org.jmailen.kotlinter") version "3.3.0"
        id("com.squareup.sqldelight") version "1.4.4"
        id("com.prof18.kmp.fatframework.cocoa") version "0.0.1"
        kotlin("multiplatform") version "1.4.31"
        kotlin("native.cocoapods") version "1.4.31"
        kotlin("android") version "1.4.31"
    }
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.contains("com.android")) {
                useModule("com.android.tools.build:gradle:7.0.0-alpha09")
            }
        }
    }

}
rootProject.name = "MyApplication"


include(":androidApp")
include(":shared")

