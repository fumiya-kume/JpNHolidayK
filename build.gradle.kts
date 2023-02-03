import org.jetbrains.dokka.gradle.DokkaTask
plugins {
    kotlin("jvm") version "1.8.0"
    `java-library`
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