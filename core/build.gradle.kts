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
    implementation (project(":api:"))

}
tasks {
    shadowJar {
        archiveFileName.set("RecoverHealth-${project.version}.jar")

        relocate("me.fixeddev", "${project.group}.recoverhealth.libs.commandflow")
        relocate("team.unnamed.inject", "${project.group}.recoverhealth.libs.inject")

    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}