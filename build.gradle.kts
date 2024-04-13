val parentProjectDir = projectDir

plugins {
    id(Plugins.kotlin) version PluginVers.kotlin apply false
    id(Plugins.detekt) version PluginVers.detekt
    id(Plugins.spring_dependency_management) version PluginVers.spring_dependency_management
    id(Plugins.owasp_dependencies) version PluginVers.owasp_dependencies
}

configurations.all {
    resolutionStrategy {
        failOnVersionConflict()
        failOnNonReproducibleResolution()
    }
}

subprojects {
    configurations.all {
        resolutionStrategy {
            eachDependency {
                requested.version?.contains("snapshot", true)?.let {
                    if (it) {
                        throw GradleException("Snapshot found: ${requested.name} ${requested.version}")
                    }
                }
            }
        }
    }

    apply {
        plugin("java")
        plugin(Plugins.kotlin)
        plugin(Plugins.detekt)
        plugin("jacoco")
        plugin(Plugins.spring_dependency_management)
        plugin(Plugins.owasp_dependencies)
    }

    repositories {
        mavenCentral()
        mavenLocal()
    }

    val implementation by configurations

    dependencies {
        implementation(platform(BOMs.spring_boot_bom))
        implementation(platform(BOMs.spring_cloud_bom))
        implementation(platform(BOMs.test_containers_bom))
        implementation(platform(BOMs.micrometer_bom))
    }

    detekt {
        config = files("$parentProjectDir/tools/detekt/detekt-config.yml")
        buildUponDefaultConfig = true
        source = files("src/main/kotlin", "src/test/kotlin", "src/testInt/kotlin")

        dependencies {
            detektPlugins("${Plugins.detekt_formatting}:${PluginVers.detekt_formatting}")
        }
    }

    tasks {

        val check = named<DefaultTask>("check")

        val jacocoTestReport = named<JacocoReport>("jacocoTestReport")
        val jacocoTestCoverageVerification = named<JacocoCoverageVerification>("jacocoTestCoverageVerification")

        check {
            finalizedBy(jacocoTestReport)
        }

        jacocoTestReport {
            dependsOn(check)
            finalizedBy(jacocoTestCoverageVerification)

            reports {
                html.required.value(true)
            }
        }

        jacocoTestCoverageVerification {
            dependsOn(jacocoTestReport)

            violationRules {

                rule {
                    excludes = listOf("app")
                    limit {
                        minimum = BigDecimal("0.5")
                    }
                }
            }
        }

        val failOnWarning = project.properties["allWarningsAsErrors"] != null && project
            .properties["allWarningsAsErrors"] == "true"

        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
                allWarningsAsErrors = failOnWarning
                freeCompilerArgs = listOf("-Xjvm-default=all-compatibility")
            }
        }

        withType<JavaCompile> {
            options.compilerArgs.add("-Xlint:all")
            sourceCompatibility = JavaVersion.VERSION_17.toString()
            targetCompatibility = JavaVersion.VERSION_17.toString()
        }

        withType<Test> {
            useJUnitPlatform()

            testLogging {
                events(
                    org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
                )
                showStandardStreams = true
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            }

            systemProperties["pact.rootDir"] = layout.buildDirectory.dir("pacts").get().asFile.absolutePath
        }
    }
}
