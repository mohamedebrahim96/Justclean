
<p align="center">
    <a>
      <img src="https://user-images.githubusercontent.com/16405013/101181838-ca16fb80-3666-11eb-827f-e365be8ca63c.png"/>
    </a>
    <h1 align="center">Justclean</h1>
</p>
Justclean, the #1 Laundry &amp; Dry cleaning services app in the GCC. Schedule a Collection &amp; Delivery from your favorite laundry around you and have your clean laundry delivered in no time!


## ✨ Screenshots
| Main Screen | comments |  Favourite |
|:-:|:-:|:-:|
| ![Fist](https://user-images.githubusercontent.com/16405013/101182273-4c9fbb00-3667-11eb-959b-606328253100.jpg) | ![3](https://user-images.githubusercontent.com/16405013/101182371-6a6d2000-3667-11eb-88ff-a738b3bdfdec.jpg) | ![3](https://user-images.githubusercontent.com/16405013/101182422-7d7ff000-3667-11eb-95fe-b89313d5f621.jpg) |

## Download
Go to the [Releases](https://github.com/mohamedebrahim96/Justclean/releases) to download the latest APK.

## 🚀 Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
*   Android Studio 3.2+
*   Java JDK

### Installing
Follow these steps if you want to get a local copy of the project on your machine.

#### 1. Clone or fork the repository by running the command below	
```
git https://github.com/mohamedebrahim96/Justclean.git
```

#### 2. Import the project in AndroidStudio, and add API Key
1.  In Android Studio, go to File -> New -> Import project
2.  Follew the dialog wizard to choose the folder where you cloned the project and click on open.
3.  Android Studio imports the projects and builds it for you

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Hilt (alpha) for dependency injection.
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Sandwich](https://github.com/skydoves/Sandwich) - construct lightweight http API response and handling error responses.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [TransformationLayout](https://github.com/skydoves/transformationlayout) - implementing transformation motion animations.
- [WhatIf](https://github.com/skydoves/whatif) - checking nullable object and empty collections more fluently.
- [Timber](https://github.com/JakeWharton/timber) - logging.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
- [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - WorkManager is an API that makes it easy to schedule deferrable, asynchronous tasks that are expected to run even if the app exits or the device restarts.


## Architecture
Justclean is based on MVVM architecture and a repository pattern.

![architecture](https://user-images.githubusercontent.com/24237865/77502018-f7d36000-6e9c-11ea-92b0-1097240c8689.png)

## Open API

Justclean using the [jsonplaceholderAPI](https://jsonplaceholder.typicode.com/) for constructing RESTful API.<br>
jsonplaceholderAPI provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to Posts.


## 📝 License
This project is released under the MIT license.
See [LICENSE](./LICENSE) for details.

```
MIT License

Copyright (c) 2020 Mohamed Ebrahim

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
