plugins {
    `java-library`
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

tasks {
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }

    compileJava {
        options.compilerArgs.add("-parameters")
    }
}