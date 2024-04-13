plugins {
    id(Plugins.spring_kotlin) version PluginVers.spring_kotlin
}

dependencies {
    implementation(project(":domain"))

    // kotlin
    implementation(Libs.kotlin_jdk8)
    implementation(Libs.kotlin_reflect)
    implementation(Libs.kotlin_stdlib)

    // spring
    implementation(Libs.spring_boot_starter)
    implementation(Libs.spring_boot_starter_actuator)

    // metrics
    implementation(Libs.micrometer_core)
    implementation(Libs.micrometer_registry_prometheus)
    implementation(Libs.micrometer_observation)

    // tests
    testImplementation(Libs.junit_params)
    testRuntimeOnly(Libs.junit_engine)
}