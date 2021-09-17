<h1 align="center">
  storyblok-mp-SDK-sample *WIP*
</h1>

<p align="center">
    ... a showcase of the Storyblok Kotlin Multiplatform Client SDK. (Android, JVM, JS, iOS, ...)
</p>

Minimal **Kotlin Multiplatform** project with Jetpack Compose, Compose for Desktop, Compose for Web, and Kotlin/JS + React clients along with Ktor backend. Currently running on
* Android (Jetpack Compose)
* Wear OS (Jetpack Compose)
* Desktop (Compose for Desktop)
* Web (Compose for Web)
* Web (Kotlin/JS + React Wrapper) (contributed by https://github.com/PatilShreyas)


### Building

You need to use Android Studio Arctic Fox (**note: Java 11 is now the minimum version required**).

Before executing the samples you have to replace `YOUR_STORYBLOK_TOKEN` with your Storyblok token inside the `gradle.properties`.

<details open><summary><b>Android / Wear app</b></summary>
<p>

Run the Android app or WearOS app via Android Studio.

</p>
</details>
<details><summary><b>React web client</b></summary>
<p>

```bash
./gradlew :web:browserDevelopmentRun
```

</p>
</details>
<details><summary><b>Compose for Web client</b></summary>
<p>

The Compose for Web client resides in the `compose-web` module and can be run by invoking:

```bash
./gradlew :compose-web:jsBrowserDevelopmentRun
```

</p>
</details>
<details><summary><b>Compose for Desktop client</b></summary>
<p>

This client is available in `compose-desktop` module.  Note that you need to use appropriate version of JVM when running (works for example with Java 11)

```bash
./gradlew :compose-desktop:run
```

</p>
</details>


### Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
* [Ktor client library](https://github.com/ktorio/ktor)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
* [Koin](https://github.com/InsertKoinIO/koin)
* [Jetpack Compose](https://developer.android.com/jetpack/compose)

## Credits

This project is heavily based on the amazing [PeopleInSpace](https://github.com/joreilly/PeopleInSpace) sample by @joreilly

## Maintained By

* Mike Penz
 * [mikepenz.com](http://mikepenz.com) - <mikepenz@gmail.com>
 * [paypal.me/mikepenz](http://paypal.me/mikepenz)

## License

    Copyright 2021 Mike Penz

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.