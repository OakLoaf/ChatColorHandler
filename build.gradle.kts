plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow") version("9.3.1")
}

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    group = "org.lushplugins"
    version = "8.0.0"

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") // Spigot
        maven("https://repo.papermc.io/repository/maven-public/") // Paper
        maven("https://repo.helpch.at/releases") // PlaceholderAPI
    }

    dependencies {
        // Libraries
        compileOnly("org.jetbrains:annotations:26.1.0")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))

        registerFeature("optional") {
            usingSourceSet(sourceSets["main"])
        }

        withSourcesJar()
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }
    }

    publishing {
        repositories {
            maven {
                name = "lushReleases"
                url = uri("https://repo.lushplugins.org/releases")
                credentials(PasswordCredentials::class)
                authentication {
                    isAllowInsecureProtocol = true
                    create<BasicAuthentication>("basic")
                }
            }

            maven {
                name = "lushSnapshots"
                url = uri("https://repo.lushplugins.org/snapshots")
                credentials(PasswordCredentials::class)
                authentication {
                    isAllowInsecureProtocol = true
                    create<BasicAuthentication>("basic")
                }
            }
        }
    }
}
