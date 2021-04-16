plugins {
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
    id("com.github.johnrengelman.shadow") version "6.1.0"

    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("com.adarshr.test-logger") version "2.1.1"
    id("net.rdrei.android.buildtimetracker") version "0.11.0"
}

object Versions {
    const val JDA = "4.2.0_252"
    const val KotlinxCoroutinesCore = "1.4.3"
    const val KotlinxSerializationJson = "1.1.0"
    const val kaml = "0.29.0"

    const val KotlinLogging = "2.0.4"
    const val Logback = "1.2.3"
    const val jansi = "1.18"

    const val JUnit = "5.7.0"
}

object Libraries {
    const val JDA = "net.dv8tion:JDA:${Versions.JDA}"
    const val KotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KotlinxCoroutinesCore}"
    const val KotlinxSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KotlinxSerializationJson}"
    const val kaml = "com.charleskorn.kaml:kaml:${Versions.kaml}"

    const val KotlinLogging = "io.github.microutils:kotlin-logging:${Versions.KotlinLogging}"
    const val LogbackCore = "ch.qos.logback:logback-core:${Versions.Logback}"
    const val LogbackClassic = "ch.qos.logback:logback-classic:${Versions.Logback}"
    const val Jansi = "org.fusesource.jansi:jansi:${Versions.jansi}"
    const val JUnitJupiter = "org.junit.jupiter:junit-jupiter:${Versions.JUnit}"

    val ExperimentalAnnotations = setOf(
        "kotlin.time.ExperimentalTime"
    )
}

repositories {
    mavenCentral()

    maven(url = "https://m2.dv8tion.net/releases")
}

dependencies {
    implementation(Libraries.JDA) {
        exclude(module = "opus-java")
    }
    implementation(Libraries.KotlinxCoroutinesCore)
    implementation(Libraries.KotlinxSerializationJson)
    implementation(Libraries.kaml)

    implementation(Libraries.KotlinLogging)
    implementation(Libraries.LogbackCore)
    implementation(Libraries.LogbackClassic)
    implementation(Libraries.Jansi)

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation(Libraries.JUnitJupiter)
}

kotlin {
    target {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
                apiVersion = "1.4"
                languageVersion = "1.4"
                allWarningsAsErrors = true
                verbose = true
            }
        }
    }

    sourceSets.all {
        languageSettings.progressiveMode = true

        Libraries.ExperimentalAnnotations.forEach {
            languageSettings.useExperimentalAnnotation(it)
        }
    }
}

/*
 * Tests
 */

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
    ignoreFailures.set(true)
}

buildtimetracker {
    reporters {
        register("summary") {
            options["ordered"] = "true"
            options["barstyle"] = "ascii"
            options["shortenTaskNames"] = "false"
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        events("passed", "failed")
    }

    testlogger {
        theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL
    }
}

task<JavaExec>("run") {
    dependsOn("build")

    group = "application"
    main = "blue.starry.steward.MainKt"
    classpath(configurations.runtimeClasspath, tasks.jar)
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    manifest {
        attributes("Main-Class" to "blue.starry.steward.MainKt")
    }
}
