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

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:4.1.0")

}

springBoot {
    mainClass.set("com.tveritin.EurekaApplication")
}
