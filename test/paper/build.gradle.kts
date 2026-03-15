plugins {
    id("xyz.jpenilla.run-paper") version ("2.2.4")
}

dependencies {
    // Dependencies
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")

    // Libraries
    implementation(project(":platform:paper"))
    implementation("io.github.revxrsal:lamp.common:4.0.0-rc.16")
    implementation("io.github.revxrsal:lamp.bukkit:4.0.0-rc.16")
}

tasks {
    processResources{
        expand(project.properties)

        inputs.property("version", rootProject.version)
        filesMatching("plugin.yml") {
            expand("version" to rootProject.version)
        }
        filesMatching("paper-plugin.yml") {
            expand("version" to rootProject.version)
        }
    }

    runServer {
        minecraftVersion("1.21.11")

        downloadPlugins {
            hangar("PlaceholderAPI", "2.11.6")
            modrinth("miniplaceholders", "4zOT6txC")
            modrinth("viaversion", "5.7.2")
            modrinth("viabackwards", "5.7.2")
        }
    }
}