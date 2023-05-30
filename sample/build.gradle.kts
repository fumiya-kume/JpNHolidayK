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

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
