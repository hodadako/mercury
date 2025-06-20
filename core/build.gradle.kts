import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.libsDirectory

buildscript {
    repositories {
        mavenLocal()
        google()
        gradlePluginPortal()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.24")
    }
}

plugins {
    java
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(libs.icu4j)
    implementation(libs.juniversalchardet)
}