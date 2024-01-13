import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    idea
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.graalvm.buildtools.native") version "0.9.27"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

group = "org.injinity"

version = if (project.hasProperty("projVersion"))
    project.property("projVersion")!!
else
    "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

fun getProperty(name: String): String? {
    val property = project.findProperty(name) as String?
    return if (property == "") null else property
}

tasks.named<BootBuildImage>("bootBuildImage") {
    // https://github.com/dashaun/paketo-arm64
    // we are using dashaun's builder for arm64 support
    builder.set("dashaun/builder:tiny")
    environment = mapOf("BP_NATIVE_IMAGE" to "true")

    val env = getProperty("projEnv") ?: "local"
    when (env) {
        !in listOf(
            "local",
            "dev",
            "stag",
            "prod"
        ) -> throw IllegalArgumentException("Invalid environment: \"$env\". List of environments: local, dev, stag, prod")

        else -> if (env != "local") publish.set(true)
    }

    getProperty("projOrg")?.let { org ->
        imageName.set("${getProperty("registryUrl")}/$org/${project.name}:$version-$env")
    } ?: run {
        imageName.set("${getProperty("registryUrl")}/${project.name}:$version-$env")
    }

    docker {
        publishRegistry.apply {
            username.set(getProperty("registryUsername"))
            password.set(getProperty("registryPassword"))
        }
    }

    createdDate.set("now")
}