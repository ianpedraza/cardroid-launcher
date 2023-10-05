plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.cardroidlauncher.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cardroidlauncher.app"
        minSdk = 24
        targetSdk = 33
        versionCode = 6
        versionName = "1.0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // App
    implementation(Dependencies.AndroidX.core)
    implementation(Dependencies.AndroidX.lifecycle)
    implementation(Dependencies.AndroidX.activityCompose)
    implementation(Dependencies.AndroidX.navigationCompose)
    implementation(Dependencies.Kotlin.reflect)

    implementation(platform(Dependencies.Compose.core))
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.graphics)
    implementation(Dependencies.Compose.uiToolingPreview)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.iconsExtended)
    implementation(Dependencies.Compose.coilCompose)

    // datastore
    implementation(Dependencies.AndroidX.datastorePreferences)

    // room
    implementation(Dependencies.AndroidX.room)
    ksp(Dependencies.AndroidX.roomCompiler)

    // drawablePainter
    implementation(Dependencies.Accompanist.drawablePainter)

    // network
    implementation(Dependencies.Network.gson)

    // di
    implementation(Dependencies.DependencyInjection.core)
    kapt(Dependencies.DependencyInjection.compiler)
    implementation(Dependencies.AndroidX.hiltNavigationCompose)

    // unitTest
    testImplementation(Dependencies.UnitTest.core)
    testImplementation(Dependencies.UnitTest.archComponents)
    testImplementation(Dependencies.UnitTest.mockito)
    testImplementation(Dependencies.UnitTest.mockitoKotlin)
    testImplementation(Dependencies.UnitTest.mockitoInline)
    testImplementation(Dependencies.UnitTest.coroutines)
    testImplementation(Dependencies.UnitTest.hamcrest)

    // androidTest
    androidTestImplementation(Dependencies.AndroidTest.junit)
    androidTestImplementation(Dependencies.AndroidTest.espresso)
    androidTestImplementation(Dependencies.Compose.ui)
    androidTestImplementation(Dependencies.AndroidTest.composeUi)

    // tools
    debugImplementation(Dependencies.Tools.composeUiTooling)
    debugImplementation(Dependencies.Tools.composeUiTooling)
}

object Dependencies {

    object AndroidX {

        internal object Versions {
            const val core = "1.12.0"
            const val lifecycle = "2.6.2"
            const val datastore_preferences = "1.0.0"
            const val activity_compose = "1.7.2"
            const val navigation_compose = "2.7.3"
            const val hilt_navigation_compose = "1.0.0"
            const val room = "2.5.2"
        }

        const val core = "androidx.core:core-ktx:${Versions.core}"

        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

        const val activityCompose =
            "androidx.activity:activity-compose:${Versions.activity_compose}"

        const val navigationCompose =
            "androidx.navigation:navigation-compose:${Versions.navigation_compose}"

        const val datastorePreferences =
            "androidx.datastore:datastore-preferences:${Versions.datastore_preferences}"

        const val hiltNavigationCompose =
            "androidx.hilt:hilt-navigation-compose:${Versions.hilt_navigation_compose}"

        const val room = "androidx.room:room-ktx:${Versions.room}"

        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Kotlin {
        internal object Versions {
            const val kotlin_reflect = "1.9.0"
        }

        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_reflect}"
    }

    object Accompanist {
        internal object Versions {
            const val core = "0.32.0"
        }

        const val drawablePainter =
            "com.google.accompanist:accompanist-drawablepainter:${Versions.core}"
    }

    object Network {
        internal object Versions {
            const val gson = "2.10.1"
        }

        const val gson = "com.google.code.gson:gson:${Versions.gson}"
    }

    object Compose {
        internal object Versions {
            const val core = "2023.03.00"
            const val coil_compose = "2.4.0"
        }

        const val core = "androidx.compose:compose-bom:${Versions.core}"
        const val ui = "androidx.compose.ui:ui"
        const val graphics = "androidx.compose.ui:ui-graphics"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val material3 = "androidx.compose.material3:material3"
        const val iconsExtended = "androidx.compose.material:material-icons-extended"

        const val coilCompose = "io.coil-kt:coil-compose:${Versions.coil_compose}"
    }

    object DependencyInjection {
        internal object Versions {
            const val hilt = "2.47"
        }

        const val core = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object UnitTest {
        internal object Versions {
            const val junit = "4.13.2"
            const val architectural_components = "2.2.0"
            const val mockito = "4.0.0"
            const val coroutines = "1.6.4"
            const val hamcrest = "2.2"
        }

        const val core = "junit:junit:${Versions.junit}"
        const val archComponents =
            "androidx.arch.core:core-testing:${Versions.architectural_components}"

        const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
        const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockito}"

        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

        const val hamcrest = "org.hamcrest:hamcrest:${Versions.hamcrest}"
    }

    object AndroidTest {
        internal object Versions {
            const val junit = "1.1.5"
            const val espresso = "3.5.1"
        }


        const val junit = "androidx.test.ext:junit:${Versions.junit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val composeUi = "androidx.compose.ui:ui-test-junit4"
    }

    object Tools {
        const val composeUiTooling = "androidx.compose.ui:ui-tooling"
        const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    }
}
