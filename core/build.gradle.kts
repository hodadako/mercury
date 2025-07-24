plugins {
    java
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    id("com.vanniktech.maven.publish") version "0.34.0"
    id("jacoco")
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

mavenPublishing {
    coordinates(
        groupId = "io.github.hodadako",
        artifactId = "mercury-core",
        version = "0.1.0"
    )

    pom {
        name.set("mercury-core")
        description.set("DNA to Text Translator")
        inceptionYear.set("2025")
        url.set("https://github.com/hodadako/mercury")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("hodadako")
                name.set("Byungryong Ko")
                email.set("hodako2401@gmail.com")
            }
        }
        scm {
            connection.set("scm:git:git@github.com:hodadako/mercury.git")
            developerConnection.set("scm:git:git@github.com:hodadako/mercury.git")
            url.set("https://github.com/hodadako/mercury.git")
        }
        issueManagement {
            system.set("GitHub Issues")
            url.set("https://github.com/hodadako/mercury/issues")
        }
    }
    publishToMavenCentral(true)
    signAllPublications()
}
