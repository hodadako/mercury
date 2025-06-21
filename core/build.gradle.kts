plugins {
    java
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(libs.bundles.encoding)
    testImplementation(libs.bundles.test)
}

tasks.test {
    useJUnitPlatform()
}
