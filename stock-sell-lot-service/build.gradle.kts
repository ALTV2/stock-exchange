plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.openapi.generator") version "7.1.0"
    id("org.jetbrains.kotlin.kapt") version "1.5.20"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")
//    implementation(project(mapOf("path" to ":common:database")))
//    implementation("io.hypersistence:hypersistence-utils-hibernate-62:3.6.0")
//    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    //MAPSTRUCT
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")

    //LOMBOK
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    //OPENAPI
    implementation("org.springframework.boot:spring-boot-starter-validation")

    //KAFKA
    implementation("org.springframework.kafka:spring-kafka:3.1.2")
    implementation("org.apache.kafka:kafka-clients:3.6.0")

//    implementation(project(":common:logging"))

    //TESTS
    implementation("org.postgresql:postgresql:42.2.27")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.1.0")
    annotationProcessor("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.1.0")

    runtimeOnly("com.h2database:h2")
}

openApiGenerate {
    generatorName.set("spring")
    validateSpec.set(true)
    inputSpec.set("$projectDir/src/main/resources/api/sell-service-openapi.yml") // path to spec
    outputDir.set("${layout.buildDirectory.asFile.get()}/generated/sources/annotationProcessor/java/main")
    apiPackage.set("com.tveritin.controller")
    modelPackage.set("com.tveritin.model.dto")
    generateApiTests.set(false)
    generateModelTests.set(false)
    generateModelDocumentation.set(false)

    globalProperties.set(
        mapOf(
            "generateSupportingFiles" to "false",
            "models" to "", // generate all models
            "apis" to "", // generate all apis
        ),
    )

    configOptions.set(
        mapOf(
            "documentationProvider" to "none",
            "generatedConstructorWithRequiredArgs" to "true",
            "openApiNullable" to "false",
            "useSpringBoot3" to "true",
            "java8" to "false",
            "skipDefaultInterface" to "true",
            "interfaceOnly" to "true",
            "serviceInterface" to "true",
            "useTags" to "true",
            "fullJavaUtil" to "false",
            "hideGenerationTimestamp" to "true",
            "sourceFolder" to "",
            "library" to "spring-boot",
            "serializationLibrary" to "jackson",
        ),
    )
}

sourceSets.main {
    java.srcDirs("${layout.buildDirectory.asFile.get()}/generated/sources/annotationProcessor/java/main")
}

kapt {
    arguments {
        arg("mapstruct.defaultComponentModel", "spring")
        arg("spring.bean.ignore", "false")
    }
}

tasks.test {
    useJUnitPlatform()
}

springBoot {
    mainClass.set("com.tveritin.SellLotApplication")
}

tasks.compileJava {
    dependsOn("openApiGenerate")
}