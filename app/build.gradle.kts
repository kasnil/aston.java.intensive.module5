plugins {
    id("java")

    // Apply the Application plugin to add support for building an executable JVM application.
    application
}

dependencies {
    implementation(project(":model"))
    implementation(project(":utils"))
}