import java.util.Date

plugins {
    java
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "edu.dio"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

var mapStructVersion = "1.6.3"

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4")

    implementation("org.springframework.boot:spring-boot-starter-validation")

    // MapStruct
    implementation("org.mapstruct:mapstruct:$mapStructVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapStructVersion")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    // DevTools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // H2 Database
    runtimeOnly("com.h2database:h2")

    // Testes
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.amqp:spring-rabbit-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named("build") {
    doLast {
        var trigger = file("src/main/resources/trigger.txt")
        if (!trigger.exists()) {
            trigger.createNewFile()
        }
        trigger.writeText(Date().time.toString())
    }
}

tasks.named<JavaExec>("bootRun") {
    jvmArgs = listOf("-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:5005")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
