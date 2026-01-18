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

dependencies {
    testImplementation(platform(Libraries.jupiterBomTest))
    testImplementation(Libraries.jupiterTest)
    testRuntimeOnly(Libraries.jupiterPlatformLauncherTest)
}

tasks.test {
    useJUnitPlatform()
}