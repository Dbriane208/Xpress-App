plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "daniel.brian.xpressapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "daniel.brian.xpressapp"
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

    buildTypes.forEach {
        it.buildConfigField("String", "CONSUMER_KEY","\"QL7S1jYVxSZNZ8TrUAlImRVqiH3IEvV5p2Wd43P1EKke0ZkL\"" )
        it.buildConfigField("String", "CONSUMER_SECRET", "\"iG66xMbrtE4rVpf2gO8OSbnq03nLjOkyGoj08zs4WPUGEwoxnxmq5pNEfgk45GcU\"")
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")

    //loading button
    implementation("br.com.simplepass:loading-button-android:2.2.0")

    // Mpesa-stk push
    implementation ("com.jakewharton.timber:timber:5.0.1")

    implementation("cn.pedant.sweetalert:library:1.3")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okio:okio:3.6.0")
}