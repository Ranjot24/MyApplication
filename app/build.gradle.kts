plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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

    buildFeatures {
        viewBinding = true
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
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.20")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0-rc01")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation("androidx.compose.ui:ui:1.6.0-beta02")
    implementation("androidx.compose.material:material:1.6.0-beta02")
    implementation("androidx.compose.ui:ui-tooling:1.6.0-beta02")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    implementation("com.stripe:stripe-android:20.35.0")
    implementation("com.stripe:stripe-android-view:20.35.0")
    implementation("com.google.android.libraries.places:places:3.3.0")
    implementation("androidx.room:room-common:2.6.0")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.3.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("androidx.room:room-runtime:2.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("com.stripe:stripecardscan:20.35.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("org.testng:testng:6.9.6")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    annotationProcessor("androidx.room:room-compiler:2.6.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
    // Add other dependencies as needed
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

apply (plugin = "com.google.gms.google-services")
apply (plugin = "androidx.navigation.safeargs")


