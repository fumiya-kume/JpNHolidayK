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

//tasks.create("javadocJar", Jar::class) {
//    dependsOn("dokkaJavadoc")
//    archiveClassifier.set("javadoc")
//    from(File(buildDir, "docs/javadoc"))
//}

//tasks.create("sourcesJar", Jar::class) {
//    dependsOn("classes")
//    archiveClassifier.set("sources")
//    from(sourceSets["main"].allSource)
//}

java {
    withJavadocJar()
    withSourcesJar()
}

//publishing {
//    repositories {
//        maven {
//            name = "OSSRH"
//            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
//            credentials {
//                username = System.getenv("MAVEN_USERNAME")
//                password = System.getenv("MAVEN_PASSWORD")
//            }
//        }
//    }
//    publications {
////        create<MavenPublication>("bintray") {
//            create<MavenPublication>("mavenJava") {
//                from(components["java"])
//                pom{
//                    artifact("$buildDir/outputs/aar/${base.archivesName}-release.aar")
////                    artifact(tasks["sourcesJar"])
////                    artifact(tasks["javadocJar"])
//                    artifact(tasks["sourcesJar"])
//                    name.set("kuu")
//                    description.set("description")
//                    url.set("url")
//                    licenses{
//                        license{
//                            name.set("The Apache License, Version 2.0")
//                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//                            distribution.set("repo")
//                        }
//                    }
//                    withXml {
//                        val node = asNode()
//                        node.appendNode("developers").appendNode("developer").apply {
//                            appendNode("id", ProjectProperties.developerId)
//                            appendNode("name", ProjectProperties.developerName)
//                        }
//                        node.appendNode("scm").apply {
//                            appendNode("connection", ProjectProperties.Url.scm)
//                            appendNode("developerConnection", ProjectProperties.Url.scm)
//                            appendNode("url", ProjectProperties.Url.github)
//                        }
////                        val dependencies = node.appendNode("dependencies")
////                        configurations.api.get().dependencies.forEach {
////                            appendDependency(
////                                dependencies,
////                                groupId = it.group ?: "",
////                                artifactId = it.name,
////                                version = it.version ?: "",
////                                scope = "compile"
////                            )
////                        }
//                    }
//                }
//                groupId = ProjectProperties.groupId
//                artifactId = base.archivesName.get()
//                version = ProjectProperties.versionName
////            }
//        }
//        repositories {
//            maven {
//                url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
//                credentials {
//                    username = project.findProperty("sonatype_username") as? String ?: ""
//                    password = project.findProperty("sonatype_password") as? String ?: ""
//                }
//            }
//        }
//    }
//}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "JpNHolidayK"
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
                name.set("My Library")
                description.set("A concise description of my library")
                url.set("http://www.example.com/library")
                properties.set(mapOf(
                    "myProp" to "value",
                    "prop.with.dots" to "anotherValue"
                ))
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("johnd")
                        name.set("John Doe")
                        email.set("john.doe@example.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://example.com/my-library.git")
                    developerConnection.set("scm:git:ssh://example.com/my-library.git")
                    url.set("http://example.com/my-library/")
                }
            }
        }
    }
    repositories {
        maven {
            // change URLs to point to your repos, e.g. http://my.org/repo
            val releasesRepoUrl = uri(layout.buildDirectory.dir("repos/releases"))
            val snapshotsRepoUrl = uri(layout.buildDirectory.dir("repos/snapshots"))
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
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