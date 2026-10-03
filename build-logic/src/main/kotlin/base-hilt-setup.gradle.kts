import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

val android = when (val androidExt = extensions.findByName("android")) {
    is ApplicationExtension -> androidExt.buildFeatures { compose = true }
    is LibraryExtension -> androidExt.buildFeatures { compose = true }
    else -> error(
        "base-hilt-setup convention plugin should be applied after \"com.android.application\"" +
                "or \"com.android.library\" plugin."
    )
}

val libs = the<LibrariesForLibs>()

dependencies {
    "api"(libs.dagger.hilt)
    "ksp"(libs.dagger.hilt.compiller)
}