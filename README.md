# ANR-Detection [![](https://jitpack.io/v/allen-hsu/anrdetection.svg)](https://jitpack.io/#allen-hsu/anrdetection)
It's a Flipper plugin for Android ANR detection, the plugin require `Android target sdk 30 or above`
You can see the more ANR details in flipper and can let you debug easier

## Setup - Android Part

To setup the ANR-Detection plugin, take the following steps:

Ensure that you have an explicit dependency in your application's build.gradle including the plugin dependency, such as is shown in the following snippet:

in your project build.gradle add the following repository
`maven { url = uri("https://jitpack.io") }`

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

in your app build.gradle add the following dependency

```
dependencies {
  debugImplementation("com.github.allen-hsu:anrdetection:1.0.0")
}
```


Update your the onCreate method in you Application to add the ANR-Detection plugin to Flipper and the Flipper listener to ANR-Detection:
import com.allenhsu.flipper.anrdetection.ANRDetectionFlipperPlugin

```kotlin
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
```

## Setup - Flipper Desktop Part
in your flipper desktop, go to the plugin manager and search `anr-detection` and install

![截圖 2023-11-08 下午5 31 58](https://github.com/allen-hsu/ANR-Detection/assets/2819672/81f09f66-f08c-4175-b403-4f0b89fbb0ed)

and you will see the ANR detection in your left side

![截圖 2023-11-08 下午5 33 11](https://github.com/allen-hsu/ANR-Detection/assets/2819672/8d1891d2-cd25-41af-8c14-a42ddb436338)

## Screenshot

![截圖 2023-11-08 下午2 51 10](https://github.com/allen-hsu/ANR-Detection/assets/2819672/c9bf08df-d4cd-4099-9dc1-1afceea0c933)
