plugins {
    `java-gradle-plugin`
}

group = "org.jp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

gradlePlugin {
    plugins {
        create("flakyTestPlugin") {
            id = "org.jp"
            implementationClass = "org.jp.FlakyTestPlugin"
        }
    }
}