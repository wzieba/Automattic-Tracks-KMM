pluginManagement {
    plugins {
        id("org.jmailen.kotlinter") version "3.3.0"
        id("com.squareup.sqldelight") version "1.4.4"
    }
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

}
rootProject.name = "MyApplication"


include(":androidApp")
include(":shared")

