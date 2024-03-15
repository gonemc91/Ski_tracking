plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.gradlePlugin)
    kotlin("kapt")
}

android {
    namespace = "com.petproject.location_client"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.play.services.location)


    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)


    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)

}