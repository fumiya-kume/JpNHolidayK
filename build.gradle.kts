import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm") version "1.8.0"
    `java-library`
    `maven-publish`
    signing
    id("org.jetbrains.dokka") version "1.7.20"
}

group = "systems.kuu"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.7.0")

    testImplementation(kotlin("test"))
}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

tasks.named<DokkaTask>("dokkaJavadoc") {
    outputDirectory.set(File(buildDir, "docs/javadoc"))
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            repositories {
                maven {
                    url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                    credentials {
                        username = project.findProperty("sonatype_username") as? String ?: ""
                        password = project.findProperty("sonatype_password") as? String ?: ""
                    }
                }
            }
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("JpNHolidayK")
                description.set("A concise description of my library")
                packaging = "jar"
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("kuu")
                        name.set("Kuu")
                        email.set("fumiya.kume@hotmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:fumiya-kume/JpNHolidayK.git")
                    developerConnection.set("scm:git:ssh://github.com:fumiya-kume/JpNHolidayK.git")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}
fun appendDependency(
    parentNode: groovy.util.Node,
    groupId: String,
    artifactId: String,
    version: String,
    scope: String
) {
    parentNode.appendNode("dependency").apply {
        appendNode("groupId", groupId)
        appendNode("artifactId", artifactId)
        appendNode("version", version)
        appendNode("scope", scope)
    }
}

object ProjectProperties {
    const val groupId: String = "systems.kuu"
    const val name: String = "JpNHolidayK"
    const val description: String = "This library helps Japan National holiday"
    const val developerId: String = "kuu"
    const val developerName: String = "Kume Fumiya"

    private const val versionMajor: Int = 0
    private const val versionMinor: Int = 0
    private const val versionPatch: Int = 1
    const val versionName: String = "$versionMajor.$versionMinor.$versionPatch"

    object Url {
        const val site: String = "https://github.com/fumiya-kume/JpNHolidayK"
        const val github: String = "https://github.com/fumiya-kume/JpNHolidayK"
        const val scm: String = "scm:git@github.com:fumiya-kume/JpNHolidayK.git"
    }
}