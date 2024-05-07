import org.jetbrains.dokka.gradle.DokkaPlugin

buildscript {
    repositories {
        mavenCentral()
        google()
    }
}

plugins {
    id("org.jetbrains.dokka") version "1.9.20" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1" apply false
    kotlin("jvm") version "1.9.24" apply false
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