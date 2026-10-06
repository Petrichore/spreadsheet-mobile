plugins {
    id("android-library-setup")
    id("base-hilt-setup")
    id("android-compose-setup")
}

android {
    namespace = "com.tech.feature.spreadsheet"
}

dependencies {
    implementation(project(":brandbook"))
    implementation(project(":spreadsheet:api:routes"))
    implementation(project(":navigation"))

    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.hilt.navigation.compose)
}