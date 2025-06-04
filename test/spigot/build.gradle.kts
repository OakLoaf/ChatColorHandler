plugins {
    id("xyz.jpenilla.run-paper") version ("2.2.4")
}

dependencies {
    // Dependencies
    compileOnly("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")

    // Libraries
    implementation(project(":"))
    implementation("io.github.revxrsal:lamp.common:4.0.0-rc.12")
    implementation("io.github.revxrsal:lamp.bukkit:4.0.0-rc.12")
}

tasks {
    shadowJar {
        relocate("org.lushplugins.chatcolorhandler.", "org.lushplugins.chatcolorhandlerpapertest.libraries.chatcolor.")
    }

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
        minecraftVersion("1.21.1")

        downloadPlugins {
            hangar("PlaceholderAPI", "2.11.6")
            modrinth("miniplaceholders", "wck4v0R0")
        }
    }
}