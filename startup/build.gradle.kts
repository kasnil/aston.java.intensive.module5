plugins {
    id("buildsrc.convention.application-jvm")
}

dependencies {
    implementation(project(":presentation"))
    implementation(project(":utils"))
}


application {
    mainClass = "aston.java.intensive.module5.Main"
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs(
        "-XX:+UseCompactObjectHeaders",
    )
}
