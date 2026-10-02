import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.kotlin.dsl.the
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.dependencies


plugins {
    id("org.jetbrains.kotlin.plugin.compose")
}

val android = when (val androidExt = extensions.findByName("android")) {
    is ApplicationExtension -> androidExt.buildFeatures { compose = true }
    is LibraryExtension -> androidExt.buildFeatures { compose = true }
    else -> error(
        "android-compose-setup convention plugin should be applied after \"com.android.application\"" +
                "or \"com.android.library\" plugin."
    )
}

val libs = the<LibrariesForLibs>()

dependencies {
    "implementation"(libs.androidx.activity.compose)
    "implementation"(platform(libs.androidx.compose.bom))
    "implementation"(libs.androidx.compose.ui)
    "implementation"(libs.androidx.compose.ui.graphics)
    "implementation"(libs.androidx.compose.ui.tooling.preview)
    "implementation"(libs.androidx.compose.material3)

    "androidTestImplementation"(platform(libs.androidx.compose.bom))
    "androidTestImplementation"(libs.androidx.compose.ui.test.junit4)
    "debugImplementation"(libs.androidx.compose.ui.tooling)
    "debugImplementation"(libs.androidx.compose.ui.test.manifest)
}