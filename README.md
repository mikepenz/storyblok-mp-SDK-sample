<h1 align="center">
  storyblok-mp-SDK-sample *WIP*
</h1>

<p align="center">
    ... a showcase of the Storyblok Kotlin Multiplatform Client SDK. (Android, JVM, JS, iOS, ...)
</p>

-------

<p align="center">
    <a href="#whats-included-">What's included 🚀</a> &bull;
    <a href="#about-storyblok">About Storyblok</a> &bull;
    <a href="#building">Building 🛠️</a> &bull;
    <a href="#credits">Credits</a> &bull;
    <a href="#license">License 📓</a>
</p>

-------

### What's included 🚀

Minimal **Kotlin Multiplatform** project showcasing the [Storyblok Kotlin Multiplatform SDK](https://github.com/mikepenz/storyblok-mp-SDK). Currently running on
* Android (Jetpack Compose)
* Wear OS (Jetpack Compose)
* Desktop (Compose for Desktop)
* Web (Compose for Web)
* Web (Kotlin/JS + React Wrapper)

-------

## About Storyblok
- **WEBSITE** https://www.storyblok.com/
- **API DOC** https://www.storyblok.com/docs/api/content-delivery/v2

-------

## Building

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