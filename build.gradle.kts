plugins {
    kotlin("jvm") version "2.1.20"
    `maven-publish`
}

group = "com.github.NirTurjeman"
version = "1.0.2"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = group.toString()
            artifactId = "Votefy"
            version = version.toString()
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(22)
}
