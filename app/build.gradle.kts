plugins {
    id("android-application-setup")
    id("base-hilt-setup")
    id("android-compose-setup")
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
}