subprojects {
    apply(plugin = "com.gradleup.shadow")

    dependencies {
        api(project(":common"))
    }

    tasks {
        build {
            dependsOn("shadowJar")
        }
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
}