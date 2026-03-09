plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.myhiking.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.myhiking.app"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"

        manifestPlaceholders["NAVER_MAP_CLIENT_ID"] =
            project.findProperty("NAVER_MAP_CLIENT_ID") ?: "YOUR_NAVER_NCP_KEY"
        manifestPlaceholders["GOOGLE_MAPS_API_KEY"] =
            project.findProperty("GOOGLE_MAPS_API_KEY") ?: "YOUR_GOOGLE_MAPS_KEY"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
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
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.fragment:fragment-ktx:1.8.5")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    implementation("com.naver.maps:map-sdk:3.21.0")

    implementation("com.google.android.gms:play-services-maps:19.0.0")
    implementation("com.google.maps.android:android-maps-utils:3.8.2")
    implementation("com.google.android.gms:play-services-location:21.3.0")

    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    implementation("androidx.exifinterface:exifinterface:1.3.7")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
}
