buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:1.4.4")
        classpath("org.jmailen.gradle:kotlinter-gradle:3.3.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")
        classpath("com.android.tools.build:gradle:7.0.0-alpha09")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}