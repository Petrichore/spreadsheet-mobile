plugins {
    id("android-library-setup")
    id("android-compose-setup")
}

android {
    namespace = "com.tech.spreadsheet.flow"

}

dependencies {
    implementation(project(":spreadsheet"))
    api(project(":spreadsheet:api:routes"))

    implementation(libs.androidx.compose.navigation)
}