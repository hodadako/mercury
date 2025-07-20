plugins {
    java
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    id("jacoco")
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(libs.bundles.encoding)
    implementation(libs.bundles.lint)
    testImplementation(libs.bundles.test)
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}



