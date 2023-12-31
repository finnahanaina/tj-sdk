plugins {
    kotlin("multiplatform") version "1.7.10"
    id("maven-publish")
}

group = "tj.monitoring"
version = "0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val jvmMain by getting{
            dependencies {
                implementation(kotlin("test"))
                implementation("com.squareup.okhttp3:okhttp:4.7.2")
                implementation("com.google.code.gson:gson:2.8.8")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
