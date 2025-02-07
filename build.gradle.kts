plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0" // Corrigido para 2.0.0
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

application{
    mainClass.set("ApplicationKt")
}




repositories {
    mavenCentral()
}






val ktor_version = "3.0.3"
val mockkVersion = "1.13.5"


dependencies {
    testImplementation(kotlin("test"))

    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-sessions:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-server-freemarker:$ktor_version")

    implementation("org.slf4j:slf4j-api:2.0.0")
    implementation("ch.qos.logback:logback-classic:1.4.12")
    testImplementation("io.mockk:mockk:${mockkVersion}")
    implementation("io.ktor:ktor-server-sessions:2.3.0")



    implementation(platform("io.github.jan-tennert.supabase:bom:3.1.0"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.github.jan-tennert.supabase:storage-kt")
    implementation("io.ktor:ktor-client-okhttp:3.0.3")

    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")






}

tasks.test {
    useJUnitPlatform()
}

tasks.jar{
    manifest{
        attributes["Main-Class"] = application.mainClass.get()
    }
}
kotlin {
    jvmToolchain(17)
}