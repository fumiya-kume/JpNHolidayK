import org.jetbrains.dokka.gradle.DokkaPlugin

buildscript {
    repositories {
        mavenCentral()
        google()
    }
}

plugins {
    id("org.jetbrains.dokka") version "1.8.10" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1" apply false
    kotlin("jvm") version "1.8.10" apply false
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

subprojects {
    apply<org.jlleitschuh.gradle.ktlint.KtlintPlugin>()
    apply<DokkaPlugin>()

    repositories {
        mavenCentral()
    }
}