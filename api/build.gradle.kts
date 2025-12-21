plugins {
    id("java")
}

dependencies {
    api(libs.commons.bukkit)
    implementation(libs.inject)
    implementation(libs.message.core)
    implementation(libs.message.bukkit)
    implementation(libs.storage.yaml)
}