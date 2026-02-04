// The code in this file is a convention plugin - a Gradle mechanism for sharing reusable build logic.
// `buildSrc` is a Gradle-recognized directory and every plugin there will be easily available in the rest of the build.
package buildsrc.convention

plugins {
    id("buildsrc.convention.common-jvm")
    jacoco
}

dependencies {
    testImplementation(platform(Libraries.jupiterBomTest))
    testImplementation(Libraries.jupiterTest)
    testRuntimeOnly(Libraries.jupiterPlatformLauncherTest)
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    reports {
        html.apply {
            required.set(true)
        }
    }
    dependsOn(tasks.test) // tests are required to run before generating the report
}

checkstyle {
    toolVersion = "10.3.3"
    configDirectory = rootProject.file("${rootDir}/config/checkstyle")

    isShowViolations = true
    isIgnoreFailures = true
}

tasks {
    checkstyleMain {
        configFile = rootProject.file("${rootDir}/config/checkstyle/checkstyleMain.xml")
    }
    checkstyleTest {
        configFile = rootProject.file("${rootDir}/config/checkstyle/checkstyleTest.xml")
    }
}

tasks.withType<Checkstyle>().configureEach {
    reports {
        html.required = true
    }
}