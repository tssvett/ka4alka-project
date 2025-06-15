plugins {
    id("java")
    id("jacoco")
    id("checkstyle")
    id("org.springframework.boot") version "3.4.6"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "dev.tssvett"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // spring starters
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // utility libs
    implementation("org.jspecify:jspecify:1.0.0")
    compileOnly ("org.projectlombok:lombok:1.18.34")
    annotationProcessor ("org.projectlombok:lombok:1.18.34")
    testCompileOnly ("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.34")

    // telegram
    implementation("org.telegram:telegrambots-client:8.3.0")
    implementation("org.telegram:telegrambots-springboot-longpolling-starter:8.3.0")

}

tasks.test {
    useJUnitPlatform()
}