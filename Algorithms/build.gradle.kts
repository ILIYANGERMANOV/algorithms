import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    val kotest = "5.4.2"
    val junitJupiter = "5.8.2"
    //junit5 is required!
    testImplementation("org.junit.jupiter:junit-jupiter:${junitJupiter}")
    testImplementation("io.kotest:kotest-runner-junit5:${kotest}")
    testImplementation("io.kotest:kotest-assertions-core:${kotest}")
    testImplementation("io.kotest:kotest-property:${kotest}")
    testImplementation("io.kotest:kotest-framework-datatest:${kotest}")
    testImplementation("io.kotest:kotest-framework-api-jvm:${kotest}")
    testImplementation("io.kotest:kotest-framework-engine-jvm:${kotest}")
    //otherwise Kotest doesn't work...
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.6.21")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}