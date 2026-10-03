plugins {
    id("android-library-setup")
    id("android-compose-setup")
}

android {
    namespace = "com.tech.spreadsheet.api"

}

dependencies {
    implementation(project(":spreadsheet"))
    api(project(":navigation"))

    implementation(libs.androidx.compose.navigation)
}