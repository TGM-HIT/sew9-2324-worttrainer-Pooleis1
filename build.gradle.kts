plugins {
    id("java")
}

group = "tgm.ac.at.parnold"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.7.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.testng:testng:7.1.0")
    testImplementation("org.testng:testng:7.1.0")
    testImplementation("junit:junit:4.13.1")
    testImplementation("junit:junit:4.13.1")
    testImplementation("junit:junit:4.13.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}