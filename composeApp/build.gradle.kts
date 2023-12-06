import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.multiplatformResources)
    kotlin("plugin.serialization") version "1.9.21"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            // Moko Resources for iOS
            export(libs.moko.resources.ios)
            export(libs.moko.graphics.ios)

            // Moko MVVM for iOS
            export(libs.moko.mvvm.core)
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            // Image Loader
            implementation(libs.kamel.image)

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Kotlin Serialization
            implementation(libs.kotlinx.serialization.json)

            // Napier Logging
            implementation(libs.aakira.napier)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        getByName("androidMain") {
            kotlin.srcDir("build/generated/moko/androidMain/src")
        }
    }
}

android {
    namespace = "com.kmm.instagramkmm"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    sourceSets["main"].resources.exclude("src/commonMain/resources/MR")

    defaultConfig {
        applicationId = "com.kmm.instagramkmm"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
        // Moko Resources Multiplatform
        commonMainApi(libs.resources.compose)

        // Moko MVVM
        commonMainApi(libs.moko.mvvm.core)
        commonMainApi(libs.moko.mvvm.compose)
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.kmm.instagramkmm"
    iosBaseLocalizationRegion = "en"
    multiplatformResourcesSourceSet = "commonMain"
    disableStaticFrameworkWarning = true
}

