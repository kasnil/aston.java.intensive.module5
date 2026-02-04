package buildsrc.convention

repositories {
    mavenCentral()
}

plugins {
    java
    idea
    checkstyle
}

java {
    sourceCompatibility = Versions.JVM
    targetCompatibility = Versions.JVM
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs(
        "-Dfile.encoding=UTF-8",
        "-Dsun.stdout.encoding=UTF-8",
        "-Dsun.stderr.encoding=UTF-8"
    )
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}