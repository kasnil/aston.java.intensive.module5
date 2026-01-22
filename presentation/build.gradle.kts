plugins {
    id("buildsrc.convention.library-jvm")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(project(":infrastructure"))
    implementation(project(":utils"))
}

