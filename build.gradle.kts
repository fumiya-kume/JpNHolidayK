plugins {
    kotlin("jvm") version "1.8.0"
    application
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

application {
    mainClass.set("MainKt")
}