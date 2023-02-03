import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm") version "1.8.0"
    `java-library`
    `maven-publish`
    signing
    id("org.jetbrains.dokka") version "1.7.20"
}

group = "systems.kuu"
version = "1.0-SNAPSHOT"

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

tasks.create("javadocJar", Jar::class) {
    dependsOn("dokkaJavadoc")
    archiveClassifier.set("javadoc")
    from(File(buildDir, "docs/javadoc"))
}


tasks.create("sourcesJar", Jar::class) {
    dependsOn("classes")
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

publishing {
    publications {
        create<MavenPublication>("bintray") {
            create<MavenPublication>("mavenJava") {
                artifact("$buildDir/outputs/aar/${base.archivesName}-release.aar")
                artifact(tasks["sourcesJar"])
                artifact(tasks["javadocJar"])
                groupId = ProjectProperties.groupId
                artifactId = base.archivesName.get()
                version = ProjectProperties.versionName
                pom.withXml {
                    val node = asNode()
                    node.appendNode("name", ProjectProperties.name)
                    node.appendNode("description", ProjectProperties.description)
                    node.appendNode("url", ProjectProperties.Url.site)
                    node.appendNode("licenses").appendNode("license").apply {
                        appendNode("name", "The Apache License, Version 2.0")
                        appendNode("url", "http://www.apache.org/licenses/LICENSE-2.0.txt")
                        appendNode("distribution", "repo")
                    }
                    node.appendNode("developers").appendNode("developer").apply {
                        appendNode("id", ProjectProperties.developerId)
                        appendNode("name", ProjectProperties.developerName)
                    }
                    node.appendNode("scm").apply {
                        appendNode("connection", ProjectProperties.Url.scm)
                        appendNode("developerConnection", ProjectProperties.Url.scm)
                        appendNode("url", ProjectProperties.Url.github)
                    }
                    val dependencies = node.appendNode("dependencies")
                    configurations.api.get().dependencies.forEach {
                        appendDependency(
                            dependencies,
                            groupId = it.group ?: "",
                            artifactId = it.name,
                            version = it.version ?: "",
                            scope = "compile"
                        )
                    }
                    configurations.implementation.get().dependencies.forEach {
                        appendDependency(
                            dependencies,
                            groupId = it.group ?: "",
                            artifactId = it.name,
                            version = it.version ?: "",
                            scope = "runtime"
                        )
                    }
                }
            }
        }
        repositories {
            maven {
                url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
                credentials {
                    username = project.findProperty("sonatype_username") as? String ?: ""
                    password = project.findProperty("sonatype_password") as? String ?: ""
                }
            }
        }
        signing {
            sign(publishing.publications["mavenJava"])
        }
    }
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