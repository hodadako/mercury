plugins {
    java
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    id("jacoco")
    id("checkstyle")
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
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

checkstyle {
    toolVersion = "10.12.4" // 최신 버전 사용, 필요시 조정
    configFile = file("${rootProject.projectDir}/checkstyle.xml")
}

tasks.withType<Checkstyle> {
    reports {
        xml.required.set(false)
        html.required.set(true)
    }
}
