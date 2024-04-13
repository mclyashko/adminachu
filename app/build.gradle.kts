plugins {
    id(Plugins.spring_boot) version PluginVers.spring_boot
    id(Plugins.spring_kotlin) version PluginVers.spring_kotlin
}

dependencies {
    implementation(project(":rest"))
    implementation(project(":use-case"))
    implementation(project(":domain"))
    implementation(project(":db"))
    implementation(project(":metric-logger"))

    // kotlin
    implementation(Libs.kotlin_jdk8)
    implementation(Libs.kotlin_reflect)
    implementation(Libs.kotlin_stdlib)

    // spring
    implementation(Libs.spring_boot_starter_web)
    implementation(Libs.spring_boot_starter_security)

    // jackson
    implementation(Libs.jackson_kotlin)

    // logging
    implementation(Libs.slf4j_api)
    implementation(Libs.logback_classic)
    implementation(Libs.logback_core)
    implementation(Libs.logback_gelf)
    implementation(Libs.kotlin_logging)

    // tests
    testImplementation(Libs.junit_params)
    testImplementation(Libs.spring_boot_starter_test)
    testImplementation(Libs.random_beans)
    testImplementation(Libs.testcontainers)
    testImplementation(Libs.testcontainers_junit)
    testImplementation(Libs.testcontainers_postgresql)
    testRuntimeOnly(Libs.junit_engine)
}

val testIntSourceSet: SourceSet = sourceSets.create("testInt") {
    compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
    runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output
}

val testIntImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.testImplementation.get())
}

val testInt by tasks.registering(Test::class) {
    testClassesDirs = testIntSourceSet.output.classesDirs
    classpath = testIntSourceSet.runtimeClasspath

    group = "verification"
    description = "Runs integration tests"

    useJUnitPlatform()
}

tasks.check {
    dependsOn(testInt)
}

tasks.bootJar {
    archiveFileName.set("${rootProject.name}.jar")
}
