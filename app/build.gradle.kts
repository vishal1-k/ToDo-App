plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.vishal.todolist"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.vishal.todolist"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.room3.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)


    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.10.2")

    // ViewModel Compose

    // iska kaam compose ui ko viewodel ke sath connect krta hai

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.11.0")

    //For Coil URL to Imgage
    implementation("io.coil-kt:coil-compose:2.7.0")

    //For Extra Icons

    implementation("androidx.compose.material:material-icons-extended")

    //Room database


    ksp("androidx.room3:room3-compiler:3.0.3")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
}