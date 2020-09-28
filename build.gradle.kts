import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}
group = "azkz.com"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test-junit5"))
    implementation("org.apache.commons:commons-lang3:3.11")
    implementation("com.opencsv:opencsv:5.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}