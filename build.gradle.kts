import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}
group = "com.azkz"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.11")
    implementation("com.opencsv:opencsv:5.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.1")
    implementation("com.amazonaws:aws-java-sdk-s3:1.11.578")
    implementation("com.amazonaws:aws-lambda-java-events:2.2.9")

    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.register<Zip>(name = "packageBig") {
    from(tasks.compileKotlin)
    from(tasks.processResources)
    into("lib") {
        from(configurations.runtimeClasspath)
    }
}

tasks.register<Zip>("packageLibs") {
    into("java/lib") {
        from(configurations.runtimeClasspath)
    }
}