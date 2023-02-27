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
    implementation("systems.kuu:JpNHolidayK:0.0.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
