import com.android.tools.profgen.expandWildcards

plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.group6.oriyoung"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.group6.oriyoung"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    dataBinding {
        enable = true
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
    buildFeatures{
        viewBinding = true;
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.circleindicator)
    implementation(libs.viewpager2)
    implementation(libs.imageslideshow)
    implementation(libs.circleimageview)
    implementation(libs.squareimageview)
    implementation(libs.glide)
    implementation(libs.firebase.database)
    implementation(libs.notification.badge)
    implementation(libs.butterknife.compiler)
    implementation(libs.eventbus)
    implementation(libs.gson)
    implementation(libs.firebase.storage)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.google.firebase:firebase-analytics")
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))

    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth:20.2.0")
    }


