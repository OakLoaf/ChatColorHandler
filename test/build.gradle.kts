subprojects {
    group = "org.lushplugins"
    version = "2.0.0-beta"

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    tasks {
        shadowJar {
            dependsOn(":shadowJar")
        }
    }
}