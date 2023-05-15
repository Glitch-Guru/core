plugins {
	java
	id("org.jetbrains.kotlin.jvm") version "1.7.20"
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "pl.glitchguru"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.5")
	implementation("org.springframework.boot:spring-boot-starter-security:3.0.5")
	implementation("com.h2database:h2:2.1.214")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("com.google.guava:guava:31.1-jre")

	compileOnly("org.projectlombok:lombok")

	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	annotationProcessor("org.projectlombok:lombok")

	testImplementation("io.kotest:kotest-runner-junit5-jvm:5.6.2")
	testImplementation("io.kotest:kotest-assertions-core-jvm:5.6.2")
	testImplementation("io.kotest:kotest-property-jvm:5.6.2")
	testImplementation("io.kotest:kotest-extensions-spring:4.4.3")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
