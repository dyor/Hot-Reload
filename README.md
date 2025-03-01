# Hot Reload for Desktop Compose Multiplatform App

This is a Kotlin Multiplatform and Compose Multiplatform project
targeting Android, iOS, Desktop that demonstrates Hot Reload for Desktop. 

This project is derived from  
[kmp.jetbrains.com](https://kmp.jetbrains.com) (Android, iOS with shared UI, and Desktop), then some code was added from 
[github.com/JetBrains/compose-hot-reload](https://github.com/JetBrains/compose-hot-reload). I included a couple of extra items to make this work for me.  

## Detailed Steps: 

* download [kmp.jetbrains.com](https://kmp.jetbrains.com)
* follow all of the directions on the readme of [github.com/JetBrains/compose-hot-reload](https://github.com/JetBrains/compose-hot-reload) - specically:
  * in your Version Catalog/`libs.version.toml` update to `kotlin="2.1.20-Beta2"`
  * in `settings.gradle` make the following changes
  ```kotlin 
  pluginManagement {
    repositories {
        ...
        //add line below
        maven("https://packages.jetbrains.team/maven/p/firework/dev")

    }
  }
  dependencyResolutionManagement {
     repositories {
        ...
        //add line below
        maven("https://packages.jetbrains.team/maven/p/firework/dev")

    }
  }
  //add these 3 lines
  plugins{  
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
  }
  ```

  * in the composeApp level `build.kotlin.kt` make the following changes
  ```kotlin
  plugins {
    ...
    //add the next line
    id("org.jetbrains.compose.hot-reload") version "1.0.0-dev-65" // <- add this additionally
  }
  kotlin {
    ...
    jvm("desktop")
    //add the next line - NOTE this is not in the original readme 
    jvmToolchain(17)
  }
  composeCompiler {
    featureFlags.add(ComposeFeatureFlag.OptimizeNonSkippingGroups)
  }
  ```
 * Finally add a Kotlin file to desktopMain called Dev.kt

```kotlin
//this package name is automatically added - note it for the next step
package xxx.yyy.zzz

import androidx.compose.runtime.Composable
import org.jetbrains.compose.reload.DevelopmentEntryPoint

@Composable
@DevelopmentEntryPoint
fun AppEntryPoint() {
    App()
}
```

Now you are able to kick off a hot-reloadable desktop window by running this command (replacing xxx.yyy.zzz with your package name): 

```terminal 
gradle devRun -PfunName=AppEntryPoint -PclassName=xxx.yyy.zzz.DevKt
```