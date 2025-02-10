import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm")
    `java-library`
    `maven-publish`
    signing
    id("org.jetbrains.dokka")
}

group = "systems.kuu"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("systems.kuu:JpNHolidayK:1.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.2")

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
            artifactId = ProjectProperties.name
            version = ProjectProperties.versionName
            repositories {
                maven {
                    val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                    val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                    url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
                    credentials {
                        username =
                            System.getenv("sonatype_username") ?: project.findProperty("sonatype_username") as? String
                                ?: ""
                        password =
                            System.getenv("sonatype_password") ?: project.findProperty("sonatype_password") as? String
                                ?: ""
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
                name.set(ProjectProperties.name)
                description.set("A concise description of my library")
                packaging = "jar"
                url.set(ProjectProperties.Url.site)
                version = ProjectProperties.versionName
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set(ProjectProperties.developerId)
                        name.set(ProjectProperties.developerName)
                        email.set("fumiya.kume@hotmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:fumiya-kume/JpNHolidayK.git")
                    developerConnection.set("scm:git:ssh://github.com:fumiya-kume/JpNHolidayK.git")
                    url.set(ProjectProperties.Url.site)
                }
            }
        }
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
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
    const val name: String = "JpNHolidayK-kotlinx-datetime"
    const val description: String = "This library helps Japan National holiday"
    const val developerId: String = "kuu"
    const val developerName: String = "Kume Fumiya"

    private const val versionMajor: Int = 1
    private const val versionMinor: Int = 0
    private const val versionPatch: Int = 0
    private const val isSnapshotEnabled: Boolean = false
    private val snapshotString = if (isSnapshotEnabled) {
        "-SNAPSHOT"
    } else {
        ""
    }
    val versionName: String = "$versionMajor.$versionMinor.$versionPatch$snapshotString"

    object Url {
        const val site: String = "https://github.com/fumiya-kume/JpNHolidayK"
        const val github: String = "https://github.com/fumiya-kume/JpNHolidayK"
        const val scm: String = "scm:git@github.com:fumiya-kume/JpNHolidayK.git"
    }
}
