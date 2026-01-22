plugins {
    id("buildsrc.convention.application-jvm")
}

dependencies {
    implementation(project(":application"))
    implementation(project(":presentation"))
    implementation(project(":utils"))
    implementation(project(":domain"))
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
    jvmArgs = listOf("-Dfile.encoding=UTF-8")
    environment("LANG", "ru_RU.UTF-8")
    environment("LC_ALL", "ru_RU.UTF-8")
}


application {
    mainClass = "aston.java.intensive.module5.Main"
}

