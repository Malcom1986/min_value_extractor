plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("io.freefair.lombok") version "8.13"
	checkstyle
}

group = "ru.maksimlitvinov"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")
	implementation("org.apache.poi:poi-ooxml:5.4.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("net.javacrumbs.json-unit:json-unit-assertj:4.1.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
