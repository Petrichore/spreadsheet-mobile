plugins {
    id("android-application-setup")
    id("base-hilt-setup")
    id("android-compose-setup")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.tech.spreadsheet.spreadsheet"

    defaultConfig {
        applicationId = "com.tech.mobile.spreadsheet"
    }
}

dependencies {
    implementation(project(":brandbook"))
    implementation(project(":spreadsheet:api:flow"))
    implementation(project(":navigation"))
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.hilt.navigation.compose)
}