import org.jetbrains.dokka.gradle.DokkaPlugin

buildscript {
    repositories {
        mavenCentral()
        google()
    }
}

plugins {
    id("org.jetbrains.dokka") version "2.0.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.3.0" apply false
    kotlin("jvm") version "2.2.0" apply false
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