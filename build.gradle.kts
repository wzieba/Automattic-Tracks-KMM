plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library").version("8.0.0").apply(false)
    kotlin("multiplatform").version("1.8.20").apply(false)
    id("app.cash.sqldelight").version("2.0.0-alpha05").apply(false)
    id("org.jetbrains.kotlin.plugin.serialization").version("1.8.20").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
