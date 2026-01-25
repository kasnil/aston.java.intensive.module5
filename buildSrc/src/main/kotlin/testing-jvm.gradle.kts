// The code in this file is a convention plugin - a Gradle mechanism for sharing reusable build logic.
// `buildSrc` is a Gradle-recognized directory and every plugin there will be easily available in the rest of the build.
package buildsrc.convention

plugins {
    id("buildsrc.convention.common-jvm")
}

dependencies {
    testImplementation(platform(Libraries.jupiterBomTest))
    testImplementation(Libraries.jupiterTest)
    testRuntimeOnly(Libraries.jupiterPlatformLauncherTest)
}

tasks.test {
    useJUnitPlatform()
}