# RickAndMorty

## Introduction

RickAndMorty is an application build with Kotlin (XML) that consume REST API from [Rick And Morty API](https://rickandmortyapi.com/documentation). This project implemented with Clean Architecture with MVVM (Model-View-ViewModel) pattern.

## Table of Content

- [Introduction](#introduction)
- [Features](#features)
- [Libraries](#libraries)
- [Project Structure](#project-structure)
  - [App Module](#app-module)
  - [Core Module](#core-module)
- [APK Link](#apk-link)

## Features

- List of character
- Detail character
- Favorite character (include show all favorite, add/remove favorite from detail page, and remove all favorite)
- Search character

## Libraries

- Kotlin (1.9.0)
- Koin as Dependency Injection
- Coil
- Facebook Shimmer
- Retrofit & OkHttp3 for network client
- Moshi
- Logging Interceptor
- Room Database
- Android Navigation (include SafeArgs)
- Coroutine
- Mockito

## Project Structure

### App Module

- **`base`**
- **`di`**
- **`presentation`**
  - **`detail`**
  - **`favorite`**
  - **`home`**
  - **`search`**
- **`ui`**

### Core Module

- **`data`**
  - **`source`**
    - **`local`**
      - **`entity`**
      - **`room`**
    - **`remote`**
      - **`network`**
      - **`response`**
- **`di`**
- **`domain`**
  - **`character`**
    - **`mapper`**
    - **`model`**
    - **`repository`**
    - **`usecase`**
  - **`favorite`**
    - **`mapper`**
    - **`model`**
    - **`repository`**
    - **`usecase`**
- **`utils`**
  - **`ext`**

## APK Link

[Google Drive](https://drive.google.com/drive/folders/1mywvpMiKmDBaPrJ7OxEYPjzHYqivszeX?usp=sharing)
