object LibVers {
    const val spring = "6.1.5"
    const val spring_boot = "3.2.4"
    const val spring_cloud = "2022.0.4"
    const val junit = "5.10.0"
    const val random_beans = "2.6.0"
    const val testcontainers = "1.19.0"
    const val jackson = "2.15.2"
    const val slf4j = "2.0.9"
    const val logback = "1.4.11"
    const val logback_gelf = "4.0.2"
    const val kotlin_logging = "3.0.5"
    const val postgresql = "42.6.0"
    const val flyway = "9.22.2"
    const val openapi = "2.2.0"
    const val micrometer = "1.11.4"
}

object Libs {
    // Kotlin
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Global.kotlin_version}"
    const val kotlin_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Global.kotlin_version}"
    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Global.kotlin_version}"

    // Jackson
    const val jackson_kotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:${LibVers.jackson}"
    const val jackson_databind = "com.fasterxml.jackson.core:jackson-databind:${LibVers.jackson}"

    // Spring
    const val spring_boot_starter = "org.springframework.boot:spring-boot-starter:${LibVers.spring_boot}"
    const val spring_boot_starter_web = "org.springframework.boot:spring-boot-starter-web:${LibVers.spring_boot}"
    const val spring_boot_starter_data_jdbc = "org.springframework.boot:spring-boot-starter-data-jdbc:${LibVers.spring_boot}"
    const val spring_boot_starter_test = "org.springframework.boot:spring-boot-starter-test:${LibVers.spring_boot}"
    const val spring_boot_starter_actuator = "org.springframework.boot:spring-boot-starter-actuator:${LibVers.spring_boot}"
    const val spring_boot_starter_security = "org.springframework.boot:spring-boot-starter-security:${LibVers.spring_boot}"
    const val spring_transaction = "org.springframework:spring-tx:${LibVers.spring}"

    // Logging
    const val slf4j_api = "org.slf4j:slf4j-api:${LibVers.slf4j}"
    const val logback_classic = "ch.qos.logback:logback-classic:${LibVers.logback}"
    const val logback_core = "ch.qos.logback:logback-core:${LibVers.logback}"
    const val logback_gelf = "de.siegmar:logback-gelf:${LibVers.logback_gelf}"
    const val kotlin_logging = "io.github.microutils:kotlin-logging-jvm:${LibVers.kotlin_logging}"

    // Tests
    const val junit_params = "org.junit.jupiter:junit-jupiter-params:${LibVers.junit}"
    const val junit_engine = "org.junit.jupiter:junit-jupiter-engine:${LibVers.junit}"
    const val random_beans = "io.github.glytching:junit-extensions:${LibVers.random_beans}"
    const val testcontainers = "org.testcontainers:testcontainers:${LibVers.testcontainers}"
    const val testcontainers_junit = "org.testcontainers:junit-jupiter:${LibVers.testcontainers}"
    const val testcontainers_postgresql = "org.testcontainers:postgresql:${LibVers.testcontainers}"

    // Database
    const val postgresql = "org.postgresql:postgresql:${LibVers.postgresql}"
    const val flyway = "org.flywaydb:flyway-core:${LibVers.flyway}"

    // Openapi
    const val openapi = "org.springdoc:springdoc-openapi-starter-webmvc-ui:${LibVers.openapi}"

    // Metrics
    const val micrometer_core = "io.micrometer:micrometer-core:${LibVers.micrometer}"
    const val micrometer_registry_prometheus = "io.micrometer:micrometer-registry-prometheus:${LibVers.micrometer}"
    const val micrometer_observation = "io.micrometer:micrometer-observation:${LibVers.micrometer}"
}

object BOMs {
    const val spring_boot_bom = "org.springframework.boot:spring-boot-dependencies:${LibVers.spring_boot}"
    const val spring_cloud_bom = "org.springframework.cloud:spring-cloud-dependencies:${LibVers.spring_cloud}"
    const val test_containers_bom = "org.testcontainers:testcontainers-bom:${LibVers.testcontainers}"
    const val micrometer_bom = "io.micrometer:micrometer-bom:${LibVers.micrometer}"
}

object PluginVers {
    const val kotlin = Global.kotlin_version
    const val spring_boot = LibVers.spring_boot
    const val detekt = "1.23.6"
    const val detekt_formatting = detekt
    const val spring_dependency_management = "1.1.3"
    const val spring_kotlin = Global.kotlin_version
    const val owasp_dependencies = "8.2.1"
}

object Plugins {
    const val kotlin = "org.jetbrains.kotlin.jvm"
    const val spring_boot = "org.springframework.boot"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val detekt_formatting = "io.gitlab.arturbosch.detekt:detekt-formatting"
    const val spring_dependency_management = "io.spring.dependency-management"
    const val spring_kotlin = "org.jetbrains.kotlin.plugin.spring"
    const val owasp_dependencies = "org.owasp.dependencycheck"
}