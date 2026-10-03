import org.gradle.accessors.dm.LibrariesForLibs

val libs = the<LibrariesForLibs>()

dependencies {
    "implementation"(libs.androidx.core.ktx)
    "implementation"(libs.androidx.appcompat)
    "implementation"(libs.androidx.lifecycle.runtime.ktx)
    "implementation"(libs.androidx.lifecycle.viewmodel.ktx)
    "implementation"(libs.material)

    "testImplementation"(libs.junit)
}