dependencies {
    // Dependencies
    compileOnly("org.spigotmc:spigot-api:26.1-R0.1-SNAPSHOT")

    // Soft Dependencies
    compileOnly("me.clip:placeholderapi:2.12.2")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString() + ".chatcolorhandler"
            artifactId = project.name
            version = rootProject.version.toString()
            from(project.components["java"])
        }
    }
}