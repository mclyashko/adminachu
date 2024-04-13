dependencies {
    // kotlin
    implementation(Libs.kotlin_jdk8)
    implementation(Libs.kotlin_reflect)
    implementation(Libs.kotlin_stdlib)

    // tests
    testImplementation(Libs.junit_params)
    testRuntimeOnly(Libs.junit_engine)
}
