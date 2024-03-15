plugins {

    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.gradlePlugin)
    kotlin("kapt")
    alias(libs.plugins.hilt)
    id("kotlin-parcelize")
}

android {
    namespace = "com.skitracking"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.skitracking"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true

            javaCompileOptions {
                annotationProcessorOptions {
                    arguments += mapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true"
                    )
                }
            }


        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.constraintlayout.compose)

    //implementation (libs.androidx.arch.lifecycle.viewmodel)
    implementation (libs.androidx.lifecycle.viewModelCompose)

    implementation(libs.junit)
    implementation(libs.junit4)


    //DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.room.ktx)



    implementation(project(":location_client"))

    //MpAndroidChart
    //implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    //Maybe need?
    //implementation("android.arch.lifecycle:extensions:1.1.1")

   
}

kapt {
    correctErrorTypes = true
}