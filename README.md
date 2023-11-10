# ANR-Detection [![](https://jitpack.io/v/allen-hsu/anrdetection.svg)](https://jitpack.io/#allen-hsu/anrdetection)
This is a Flipper plugin for Android ANR (Application Not Responding) detection. It requires Android target SDK version `30` or above. With this plugin, you can get more detailed ANR information within Flipper, simplifying the debugging process.

![截圖 2023-11-08 下午2 51 10](https://github.com/allen-hsu/ANR-Detection/assets/2819672/c9bf08df-d4cd-4099-9dc1-1afceea0c933)

## Setup - Android Part

To integrate the ANR-Detection plugin into your Android application, follow these steps:

1. Add the JitPack repository to your project's `build.gradle`:

`maven { url = uri("https://jitpack.io") }`

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

2. Add the ANR-Detection plugin as a dependency in your app's `build.gradle`:

```kotlin
dependencies {
  debugImplementation("com.github.allen-hsu:anrdetection:1.0.2")
}
```
3. In your Application class, initialize the ANR-Detection plugin:

```kotlin
import com.allenhsu.flipper.anrdetection.ANRDetectionFlipperPlugin

class MyApplication : Application() {
    override fun onCreate() {
      super.onCreate()
      SoLoader.init(this, false)
      val client = AndroidFlipperClient.getInstance(this)
      /*
      add ANR-Detection plugin to flipper
      */
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        client.addPlugin(ANRDetectionFlipperPlugin(this))
      }
      client.start()
    }
}
```

## Setup - Flipper Desktop
To use the plugin with Flipper Desktop:

1. Open Flipper Desktop and navigate to the Plugin Manager.
![截圖 2023-11-08 下午5 31 58](https://github.com/allen-hsu/ANR-Detection/assets/2819672/81f09f66-f08c-4175-b403-4f0b89fbb0ed)
2. Search for `anr-detection` and install the plugin.
3. Once installed, the ANR detection tab will be available on the left sidebar in Flipper Desktop.
![截圖 2023-11-08 下午5 33 11](https://github.com/allen-hsu/ANR-Detection/assets/2819672/8d1891d2-cd25-41af-8c14-a42ddb436338)



