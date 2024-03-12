plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "com.tveritin"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2023.0.0"

dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway-mvc")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

springBoot {
    mainClass.set("com.tveritin.GateWay")
}
