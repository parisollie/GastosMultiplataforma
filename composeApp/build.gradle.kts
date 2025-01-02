import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("app.cash.sqldelight") version "2.0.2"
    kotlin("plugin.serialization") version "2.1.0"
}
repositories {
    google()
    mavenCentral()
}
//
sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.exampleApp.db")
        }
    }
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilations.all {
            //jvmTarget.set(JvmTarget.JVM_11)
            kotlinOptions {
                jvmTarget = "11"
            }
        }
        //Vid 71
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            //Koin, Vid 46
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.1"))
            implementation("io.insert-koin:koin-core")
            implementation("io.insert-koin:koin-android")
            //Vid 50 SQDelight
            implementation("app.cash.sqldelight:android-driver:2.0.2")
            //Vid 63,Ktor
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
            //Vid 80
            implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.3-beta")

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            //Vid 23, ponemos las librerias
            api(compose.foundation)
            api(compose.animation)
            implementation(compose.material)
            //Vid 29
            api(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            //Vid 23, Navigation Precompose
            api("moe.tlaster:precompose:1.5.10")
            //Vid 23 ,View Model
            api("moe.tlaster:precompose-viewmodel:1.5.10")
            //Vid 46 Koin
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.1"))
            implementation("io.insert-koin:koin-core")
            implementation("io.insert-koin:koin-compose")
            api("moe.tlaster:precompose-koin:1.5.10")
            //Vid 63,Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.content.negotiation)

        }
        iosMain.dependencies {
            //VID 50,ios depedencis
            implementation("app.cash.sqldelight:native-driver:2.0.2")
            implementation("co.touchlab:stately-common:2.0.5")
            //Vid 63,ktor
            implementation(libs.ktor.client.darwin)
        }
        //Vid 41,para los test
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            //vid 71
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }

    }
}

android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        //Vid 71
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    //Vid 27
    buildFeatures{
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime.android)
    debugImplementation(compose.uiTooling)
}

