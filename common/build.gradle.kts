import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("org.jetbrains.kotlin.native.cocoapods")
    id("com.chromaticnoise.multiplatform-swiftpackage") version "2.0.3"
    id("com.codingfeline.buildkonfig")
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

android {
    compileSdk = Versions.androidCompileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = Versions.androidMinSdk
        targetSdk = Versions.androidTargetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

// Workaround for https://youtrack.jetbrains.com/issue/KT-43944
android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

buildkonfig {
    packageName = "com.mikepenz.common"
    defaultConfigs {
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "STORYBLOK_TOKEN", Properties().also {
            if (rootProject.file("local.properties").exists()) {
                it.load(rootProject.file("local.properties").inputStream())
            }
        }.getProperty("storyblok.token", null) ?: project.property("storyblok.token")?.toString() ?: "")
    }
}

kotlin {
    val sdkName: String? = System.getenv("SDK_NAME")

    val isiOSDevice = sdkName.orEmpty().startsWith("iphoneos")
    if (isiOSDevice) {
        iosArm64("iOS")
    } else {
        iosX64("iOS")
    }

    val isWatchOSDevice = sdkName.orEmpty().startsWith("watchos")
    if (isWatchOSDevice) {
        watchosArm64("watch")
    } else {
        watchosX86("watch")
    }

    macosX64("macOS")
    android()
    jvm()

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Storyblok-mp-SDK-sample"
        homepage = "https://github.com/mikepenz/storyblok-mp-SDK-sample"
    }

    js(IR) {
        useCommonJs()
        browser()
    }

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(Deps.Kotlinx.coroutinesCore) {
                isForce = true
            }

            with(Deps.StoryblokSdk) {
                api(core)
            }

            with(Deps.Ktor) {
                implementation(clientCore)
                implementation(clientJson)
                implementation(clientLogging)
                implementation(clientSerialization)
            }

            with(Deps.Kotlinx) {
                implementation(serializationCore)
            }

            with(Deps.Koin) {
                api(core)
                api(test)
            }

            with(Deps.Log) {
                api(kermit)
            }
        }
        sourceSets["commonTest"].dependencies {
        }

        sourceSets["androidMain"].dependencies {
            implementation(Deps.Ktor.clientAndroid)
        }
        sourceSets["androidTest"].dependencies {
            // having issue with following after update to Kotlin 1.5.21
            // need to investigate further
            //implementation(Deps.Test.kotlinTest)
            //implementation(Deps.Test.kotlinTestJUnit)
            implementation(Deps.Test.junit)
        }

        sourceSets["jvmMain"].dependencies {
            implementation(Deps.Ktor.clientJava)
            implementation(Deps.Log.slf4j)
        }

        sourceSets["iOSMain"].dependencies {
            implementation(Deps.Ktor.clientIos)
        }
        sourceSets["iOSTest"].dependencies {
        }

        sourceSets["watchMain"].dependencies {
            implementation(Deps.Ktor.clientIos)
        }

        sourceSets["macOSMain"].dependencies {
            implementation(Deps.Ktor.clientCurl)
        }

        sourceSets["jsMain"].dependencies {
            implementation(Deps.Ktor.clientJs)
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

multiplatformSwiftPackage {
    packageName("Storyblok-mp-SDK-sample")
    swiftToolsVersion("5.3")
    targetPlatforms {
        iOS { v("13") }
    }
}