plugins{
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("java-library")
}


repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.unnamed.team/repository/unnamed-public/")
    mavenLocal()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")

    implementation ("team.unnamed:inject:1.0.1")
    implementation ("me.fixeddev:commandflow-universal:0.5.3")
    implementation ("me.fixeddev:commandflow-bukkit:0.5.2")
    implementation ("com.github.cryptomorin:xSeries:9.2.0") { isTransitive = false }
    implementation ("org.spongepowered:configurate-yaml:4.0.0")
}
tasks {
    shadowJar {
        archiveFileName.set("RecoverHealth.jar")

        relocate("me.fixeddev", "${project.group}.recoverhealth.libs.commandflow")
        relocate("team.unnamed.inject", "${project.group}.recoverhealth.libs.inject")
        relocate("com.cryptomorin.xseries", "${project.group}.recoverhealth.libs.xseries")

    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}