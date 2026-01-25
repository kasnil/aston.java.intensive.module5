package buildsrc.convention

repositories {
    mavenCentral()
}

plugins {
    java
    idea
}

java {
    sourceCompatibility = Versions.JVM
    targetCompatibility = Versions.JVM
}