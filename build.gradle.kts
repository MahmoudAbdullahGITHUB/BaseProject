// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false


}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
//    ext.hilt_version = '2.40'
    dependencies {

        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")

//        val nav_version = "2.7.4"
//        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")

    }

}
