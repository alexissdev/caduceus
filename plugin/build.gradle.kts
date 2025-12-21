plugins {
    id("alexisdev.common-conventions")
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {
    compileOnly(libs.spigot)

    implementation(libs.command)
}

tasks {
    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")
    }

    named<DefaultTask>("build") {
        dependsOn("shadowJar")
    }
}

bukkit {
    val projectName = "${findProperty("plugin-name")}"
    main = "dev.alexisdev.${projectName.toLowerCase()}.${projectName}Plugin"
    apiVersion = "1.13"
    version = "${project.version}"
    authors = listOf("AlexisDev")
    description = "${findProperty("plugin-description")}"
    name = projectName
}