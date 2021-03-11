import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.prof18.kmp.fatframework.cocoa") version "0.0.1"
    id("maven-publish")
    id("org.jmailen.kotlinter")
    id("com.squareup.sqldelight")
}

val iosFrameworkName = "shared"
val kotlinVersion = "1.4.2-native-mt"

android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

kotlin {
    android()
    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"

        // You can change the name of the produced framework.
        // By default, it is the name of the Gradle project.
        frameworkName = "my_framework"
        pod("AFNetworking") {
            source = git("https://github.com/AFNetworking/AFNetworking") {
                tag = "4.0.0"
            }
        }
    }
    ios {
//        binaries {
//            framework {
//                baseName = iosFrameworkName
//            }
//        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinVersion") {
                    version {
                        strictly(kotlinVersion)
                    }
                }
                implementation("com.squareup.sqldelight:coroutines-extensions:1.4.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation("app.cash.turbine:turbine:0.4.0")
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.3.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinVersion")
                implementation("com.squareup.sqldelight:android-driver:1.4.4")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("androidx.test:core:1.3.0")
                implementation("org.robolectric:robolectric:4.4")
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.4.4")
            }
        }
        val iosTest by getting

        matching {
            it.name.endsWith("Test")
        }.configureEach {
            languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
        }

        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
                useExperimentalAnnotation("kotlinx.coroutines.ObsoleteCoroutinesApi")
            }
        }
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
}

sqldelight {
    database("EventsDatabase") {
        this.packageName = "com.automattic.myapplication.shared"
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework =
        kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

fatFrameworkCocoaConfig {
    fatFrameworkName = iosFrameworkName
    outputPath = "$rootDir/../test-dest"
    versionName = "1.2-SNAPSHOT"

    cocoaPodRepoInfo {
        summary = "This is a test KMP framework"
        homepage = "https://github.com/wzieba/KMM-template"
        license = "MIT"
        authors = "\"user\" => \"mail@mail.com\""
        gitUrl = "git@github.com:wzieba/KMM-template-cocoa.git"
    }
}
