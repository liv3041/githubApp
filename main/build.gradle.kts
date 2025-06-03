plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    kotlin("kapt") // If you're using KAPT for annotation processing



//    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.toonandtools.main"
    compileSdk = 34

    defaultConfig {
        minSdk = 24



        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
//    annotationProcessor(libs.androidx.room.compiler)
//    implementation(libs.androidx.room.common.jvm)
//    implementation(libs.androidx.room.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Retrofit & Gson
    implementation (libs.retrofit)
    implementation(libs.converter.gson)

// Lifecycle + ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.compose)

// Coroutine support
    implementation (libs.kotlinx.coroutines.android)

    // Hilt
    implementation ("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
//    ksp("com.google.dagger:hilt-compiler:2.51") // 👈 use ksp here
//    ksp("androidx.hilt:hilt-compiler:1.0.0")


    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")

    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")


    implementation ("androidx.room:room-runtime:2.7.1")
    kapt (libs.androidx.room.compiler)

    // Optional: Kotlin Extensions and Coroutines support
    implementation ("androidx.room:room-ktx:2.7.1")

    // Optional: Testing
    testImplementation ("androidx.room:room-testing:2.7.1")




}