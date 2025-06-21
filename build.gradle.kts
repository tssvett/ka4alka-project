group = "dev.tssvett"
version = "1.0.0"

// Константы для версий зависимостей.
object Versions {
    const val SPRING_BOOT = "3.3.4"
    const val JAVA = "21"
    const val LOMBOK = "1.18.34"
    const val JACKSON_DATABIND = "2.18.0-rc1"
    const val SLF4J_API = "1.7.30"
    const val LOGBACK_CLASSIC = "1.4.12"
    const val LOGBACK_CORE = "1.4.14"
    const val LIQUIBASE_CORE = "4.29.2"
    const val POSTGRESQL = "42.7.4"
    const val SPRING_BOOT_STARTER_SECURITY = "3.3.5"
    const val JJWT_API = "0.12.6"
    const val JJWT_IMPL = "0.12.6"
    const val JJWT_JACKSON = "0.12.6"
    const val JOOQ = "3.20.1"
    const val SWAGGER = "1.6.15"
    const val SWAGGER_UI = "1.8.0"
    const val SWAGGER_UI_STARTER = "2.8.5"
    const val INVEST_CORE = "1.30"
    const val MAPSTRUCT = "1.6.3"
    const val RATE_LIMITER = "2.3.0"
}

// Константы для библиотек.
object Libraries {
    const val SPRING_FRAMEWORK_BOOT = "org.springframework.boot"
    const val LOMBOK = "org.projectlombok:lombok:${Versions.LOMBOK}"
    const val JACKSON_DATABIND = "com.fasterxml.jackson.core:jackson-databind:${Versions.JACKSON_DATABIND}"
    const val SLF4J_API = "org.slf4j:slf4j-api:${Versions.SLF4J_API}"
    const val LOGBACK_CLASSIC = "ch.qos.logback:logback-classic:${Versions.LOGBACK_CLASSIC}"
    const val LOGBACK_CORE = "ch.qos.logback:logback-core:${Versions.LOGBACK_CORE}"
    const val SPRING_BOOT_STARTER_WEB = "org.springframework.boot:spring-boot-starter-web:${Versions.SPRING_BOOT}"
    const val SPRING_BOOT_STARTER_VALIDATION =
        "org.springframework.boot:spring-boot-starter-validation:${Versions.SPRING_BOOT}"
    const val LIQUIBASE_CORE = "org.liquibase:liquibase-core:${Versions.LIQUIBASE_CORE}"
    const val POSTGRESQL = "org.postgresql:postgresql:${Versions.POSTGRESQL}"
    const val SPRING_BOOT_STARTER_SECURITY =
        "org.springframework.boot:spring-boot-starter-security:${Versions.SPRING_BOOT_STARTER_SECURITY}"
    const val SPRING_BOOT_STARTER_JOOQ = "org.springframework.boot:spring-boot-starter-jooq:${Versions.SPRING_BOOT}"
    const val SPRING_BOOT_STARTER_AOP = "org.springframework.boot:spring-boot-starter-aop:${Versions.SPRING_BOOT}"
    const val JJWT_API = "io.jsonwebtoken:jjwt-api:${Versions.JJWT_API}"
    const val JJWT_IMPL = "io.jsonwebtoken:jjwt-impl:${Versions.JJWT_IMPL}"
    const val JJWT_JACKSON = "io.jsonwebtoken:jjwt-jackson:${Versions.JJWT_JACKSON}"
    const val JOOQ_CODEGEN = "org.jooq:jooq-meta-extensions-liquibase:${Versions.JOOQ}"
    const val JOOQ = "org.jooq:jooq:${Versions.JOOQ}"
    const val SWAGGER = "io.swagger:swagger-annotations:${Versions.SWAGGER}"
    const val SWAGGER_UI = "org.springdoc:springdoc-openapi-ui:${Versions.SWAGGER_UI}"
    const val SWAGGER_UI_STARTER = "org.springdoc:springdoc-openapi-starter-webmvc-ui:${Versions.SWAGGER_UI_STARTER}"
    const val INVEST_CORE = "ru.tinkoff.piapi:java-sdk-core:${Versions.INVEST_CORE}"
    const val MAPSTRUCT = "org.mapstruct:mapstruct:${Versions.MAPSTRUCT}"
    const val MAPSTRUCT_PROCESSOR = "org.mapstruct:mapstruct-processor:${Versions.MAPSTRUCT}"
    const val RATE_LIMITER = "io.github.resilience4j:resilience4j-spring-boot3:${Versions.RATE_LIMITER}"

}

plugins {
    application
    id("org.springframework.boot") version "3.3.4"
    id("org.jooq.jooq-codegen-gradle") version "3.20.1"
    id("org.springdoc.openapi-gradle-plugin") version "1.9.0"
}

repositories {
    // Используем Maven Central для разрешения зависимостей.
    mavenCentral()
}

dependencies {
    // Telegram
    implementation("org.telegram:telegrambots-client:8.3.0")
    implementation("org.telegram:telegrambots-springboot-longpolling-starter:8.3.0")

    // Lombok
    implementation(Libraries.LOMBOK)
    annotationProcessor(Libraries.LOMBOK)

    // Jackson
    implementation(Libraries.JACKSON_DATABIND)

    // SLF4J и Logback для логирования.
    implementation(Libraries.SLF4J_API)
    implementation(Libraries.LOGBACK_CLASSIC)
    implementation(Libraries.LOGBACK_CORE)

    // Spring Boot Starter Web
    implementation(Libraries.SPRING_BOOT_STARTER_WEB)
    // Spring Boot Starter Validation
    implementation(Libraries.SPRING_BOOT_STARTER_VALIDATION)
    // Spring Boot Starter Jooq
    implementation(Libraries.SPRING_BOOT_STARTER_JOOQ)
    ///Spring Boot Starter AOP
    implementation(Libraries.SPRING_BOOT_STARTER_AOP)
    // Liquibase
    implementation(Libraries.LIQUIBASE_CORE)
    // PostgreSQL
    implementation(Libraries.POSTGRESQL)

    //JOOQ
    implementation(Libraries.JOOQ)
    // JOOQ Codegen
    jooqCodegen(Libraries.JOOQ_CODEGEN)

    // Swagger
    //implementation(Libraries.SWAGGER)
    //implementation(Libraries.SWAGGER_UI)
    implementation(Libraries.SWAGGER_UI_STARTER)

    //MapStruct
    implementation(Libraries.MAPSTRUCT)
    annotationProcessor(Libraries.MAPSTRUCT_PROCESSOR)

    //Rate Limiter
    implementation(Libraries.RATE_LIMITER)


    // Spring Boot Starter Security (для JWT, PasswordEncoder и security)
    implementation("org.springframework.boot:spring-boot-starter-security:${Versions.SPRING_BOOT_STARTER_SECURITY}")

    // Spring Security OAuth2 Resource Server (для JwtDecoder/JwtEncoder)
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server:3.4.4")

    // Nimbus JOSE + JWT (для JWK, RSAKey и JWT-операций)
    implementation("com.nimbusds:nimbus-jose-jwt:9.37.3")
    implementation("org.springframework.security:spring-security-rsa:1.1.5")
}

jooq {
    configuration {
        generator {
            database {
                name = "org.jooq.meta.extensions.liquibase.LiquibaseDatabase"
                properties {

                    property {
                        key = "rootPath"
                        value = "${project.projectDir}/src/main/resources"
                    }

                    // Specify the classpath location of your XML, YAML, or JSON script.
                    property {
                        key = "scripts"
                        value = "/migrations/changelog-master.xml"
                    }

                    // Whether you want to include liquibase tables in generated output
                    //
                    // - false (default)
                    // - true: includes DATABASECHANGELOG and DATABASECHANGELOGLOCK tables
                    property {
                        key = "includeLiquibaseTables"
                        value = false.toString()
                    }

                    // Whether you want to use jOOQ's translating ParsingConnection to translate
                    // between your dialect (e.g. Oracle), and jOOQ's in-memory H2 dialect
                    //
                    // - false (default)
                    // - true: translates e.g. from VARCHAR2(100) to VARCHAR(100)
                    property {
                        key = "useParsingConnection"
                        value = false.toString()
                    }

                    // Properties prefixed "database." will be passed on to the liquibase.database.Database class
                    // if a matching setter is found
                    property {
                        key = "database.liquibaseSchemaName"
                        value = "lb"
                    }

                    // The property "changeLogParameters.contexts" will be passed on to the
                    // liquibase.database.Database.update() call (jOOQ 3.13.2+).
                    // See https://www.liquibase.org/documentation/contexts.html
                    property {
                        key = "changeLogParameters.contexts"
                        value = "!test"
                    }
                }
                target {
                    directory = "${project.projectDir}/src/main/java/dev/tssvett/db/generated"
                    packageName ="jooq"
                }
            }
        }
    }
}

openApi {
    apiDocsUrl.set("http://localhost:8080/v3/api-docs")
    outputDir.set(file("${project.projectDir}/docs"))
    outputFileName.set("openapi.yaml")
}

// Настройка Java toolchain для использования конкретной версии Java.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(Versions.JAVA)
    }
}

// Добавление параметра -parameters для поддержки именованных параметров в Lombok.
tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-parameters")
}

// Настройка тестов для использования JUnit Platform.
tasks.named<Test>("test") {
    useJUnitPlatform()
}


tasks {
    compileJava {
        dependsOn(jooqCodegen)
    }
}