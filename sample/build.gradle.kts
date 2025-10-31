plugins {
    id("java")
    kotlin("jvm")
}

group = "systems.kuu"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("systems.kuu:JpNHolidayK:1.0.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.0.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
