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
    id("checkstyle")
}

repositories {
    mavenCentral()
    google()
}