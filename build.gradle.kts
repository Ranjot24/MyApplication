// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.7.5" apply false
}

// build.gradle (Project-level)
buildscript {
    repositories {
        google()
        mavenCentral{
            url = uri("https://repo.maven.apache.org/maven2/")
    }
        maven {
            url = uri("https://www.google.com/")
            url = uri("https://mvnrepository.com/artifact/com.stripe/stripe-android")
        }
        jcenter()
    }

    dependencies {
        // Add this line
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.android.tools.build:gradle:8.2.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
        classpath("androidx.multidex:multidex:2.0.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
    }
}
