plugins {
    id("android-application-setup")
    id("base-hilt-setup")
    id("android-compose-setup")
}

android {
    namespace = "com.tech.mobile.spreadsheet"

    defaultConfig {
        applicationId = "com.tech.mobile.spreadsheet"
    }
}

dependencies {
    implementation(project(":brandbook"))
    implementation(project(":spreadsheet:api"))
    implementation(libs.androidx.compose.navigation)
}