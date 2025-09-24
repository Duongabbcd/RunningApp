plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
//    id("com.google.gms.google-services")
    alias(libs.plugins.navigation.safe.args) // ✅ this line
}

android {
    namespace = "com.ezt.runningapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ezt.runningapp"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.intuit.sdp:sdp-android:1.1.1")
    implementation("com.auth0:java-jwt:4.4.0")

    implementation("androidx.lifecycle:lifecycle-service:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.fragment:fragment-ktx:1.8.6")

    implementation("com.google.dagger:hilt-android:2.55")
    ksp("com.google.dagger:hilt-compiler:2.55")

    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging")

    implementation("com.github.bumptech.glide:glide:4.15.1") // or your Glide version
    ksp("com.github.bumptech.glide:compiler:4.15.1")        // if using kapt
    implementation("com.github.bumptech.glide:okhttp3-integration:4.15.1")// OkHttp integration

    // Google Maps Location Services
    implementation ("com.google.android.gms:play-services-location:17.0.0")
    implementation ("com.google.android.gms:play-services-maps:17.0.0")

    implementation("com.google.dagger:hilt-android:2.55")
    ksp("com.google.dagger:hilt-compiler:2.55")

    //Room
    val roomVersion = "2.6.1" // ✅ use latest stable version

    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    // ✅ KSP compiler
    ksp("androidx.room:room-compiler:$roomVersion")

    // Easy Permissions
    implementation("pub.devrel:easypermissions:3.0.0")
}