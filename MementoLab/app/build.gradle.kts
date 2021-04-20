plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.plab.mementolab"
        minSdk = 26
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

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
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
        kotlinCompilerVersion = "1.4.32"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.0-alpha06")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")

    kapt("androidx.room:room-compiler:2.2.6")
    implementation("androidx.room:room-runtime:2.2.6")
    implementation("androidx.room:room-ktx:2.2.6")
    implementation("com.google.code.gson:gson:2.8.6")

    /*
      implementation 'androidx.core:core-ktx:1.3.2'
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.4'
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.4'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'


//    annotationProcessor 'android.arch.lifecycle:compiler:1.0.0';

    // ViewModel

    implementation "androidx.lifecycle:lifecycle-common-java8:2.3.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0"

    implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"

    // LiveData
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.3.0"

    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1'

    // Koin for Kotlin apps
    implementation "io.insert-koin:koin-android:3.0.1-beta-1"
    implementation "io.insert-koin:koin-android-ext:3.0.1-beta-1"
    implementation "io.insert-koin:koin-androidx-compose:3.0.1-beta-1"

    kapt 'com.github.bumptech.glide:compiler:4.12.0'
    implementation "com.squareup.okhttp3:okhttp:3.14.0"
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    implementation('com.github.bumptech.glide:okhttp3-integration:4.7.1') {
        exclude group: 'glide-parent'
    }
    implementation 'com.google.code.gson:gson:2.8.6'


    implementation 'androidx.compose.ui:ui:1.0.0-beta03'
    // Tooling support (Previews, etc.)
    implementation 'androidx.compose.ui:ui-tooling:1.0.0-beta03'
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation 'androidx.compose.foundation:foundation:1.0.0-beta03'
    // Material Design
    implementation 'androidx.compose.material:material:1.0.0-beta03'
    // Material design icons
    implementation 'androidx.compose.material:material-icons-core:1.0.0-beta03'
    implementation 'androidx.compose.material:material-icons-extended:1.0.0-beta03'
    // Integration with activities
    implementation 'androidx.activity:activity-compose:1.3.0-alpha05'
    // Integration with ViewModels
    implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha03'
    // Integration with observables
    implementation 'androidx.compose.runtime:runtime-livedata:1.0.0-beta03'
    implementation 'androidx.compose.runtime:runtime-rxjava2:1.0.0-beta02'
    implementation "androidx.compose.runtime:runtime:1.0.0-beta03"
    implementation "androidx.compose.foundation:foundation-layout:1.0.0-beta03"
    implementation "com.google.android.material:compose-theme-adapter:1.0.0-beta02"
    implementation "androidx.navigation:navigation-compose:1.0.0-alpha09"

    implementation "com.google.accompanist:accompanist-insets:0.7.0"
    implementation "androidx.compose.ui:ui-viewbinding:1.0.0-beta03"
    implementation "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha05"

    // UI Tests
    androidTestImplementation 'androidx.compose.ui:ui-test-junit4:1.0.0-beta03'

     */
}