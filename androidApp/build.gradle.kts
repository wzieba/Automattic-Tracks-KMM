plugins {
    id("com.android.application")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
}

android {
    namespace = "com.automattic.myapplication.androidApp"
    compileSdkVersion(33)
    defaultConfig {
        applicationId = "com.automattic.myapplication.androidApp"
        minSdkVersion(24)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}