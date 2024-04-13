plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "de.educationshare"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.16.1");
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.16.1");
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1");
    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final");
    implementation("com.mysql:mysql-connector-j:8.3.0");
    implementation("org.json:json:20240303");
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("at.favre.lib:bcrypt:0.10.2")

}

