plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
}

android {
    namespace = "uz.developers.asaxiybooks"
    compileSdk = 34

    defaultConfig {
        applicationId = "uz.developers.asaxiybooks"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
// Dots
    implementation("com.tbuonomo:dotsindicator:5.0")

//Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")


// Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

// ViewBinding
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.9")

// Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    kapt("androidx.lifecycle:lifecycle-compiler:2.7.0")

// Better Logging In Android Using Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

// Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    // GOOGLE Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-storage")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.4.0")
    kapt("com.github.bumptech.glide:compiler:4.4.0")

    /**
     *  ROOM ORM
     **/
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // pdf view
    implementation("com.github.barteksc:android-pdf-viewer:2.8.2")


    /*
    * Combine
    * */
    implementation(platform("ru.ldralighieri.corbind:corbind-bom:2023.12.00"))
    implementation("ru.ldralighieri.corbind:corbind")
    implementation("ru.ldralighieri.corbind:corbind-core")
}