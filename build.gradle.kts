buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha09")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}