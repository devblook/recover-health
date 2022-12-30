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
    implementation ("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
    implementation ("team.unnamed:inject:1.0.1")
    implementation ("me.fixeddev:commandflow-universal:0.5.3")
    implementation ("me.fixeddev:commandflow-bukkit:0.5.2")
}
