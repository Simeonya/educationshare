plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "de.educationshare"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:+");
    implementation("com.fasterxml.jackson.core:jackson-annotations:+");
    implementation("com.fasterxml.jackson.core:jackson-databind:+");
    implementation("org.hibernate.orm:hibernate-core:+");
    implementation("com.mysql:mysql-connector-j:+");
    implementation("org.json:json:+");
    implementation("org.springframework.boot:spring-boot-starter-web:+")
    implementation("at.favre.lib:bcrypt:+")
}

