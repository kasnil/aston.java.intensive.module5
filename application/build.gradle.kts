plugins {
    id("buildsrc.convention.library-jvm")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":utils"))
}