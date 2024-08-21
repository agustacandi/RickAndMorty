package com.takehomechallenge.core.utils.ext

fun String.statusState(): String {
    return when (this) {
        "Alive" -> "\uD83D\uDE01 Alive"
        "Dead" -> "\uD83D\uDC80 Dead"
        else -> "\uD83E\uDD14 Unknown"
    }
}

fun String.speciesState(): String {
    return when (this) {
        "Human" -> "\uD83D\uDC66 Human"
        "Alien" -> "\uD83D\uDC7D Alien"
        "Robot" -> "\uD83E\uDD16 Robot"
        "Cronenberg" -> "\uD83E\uDDDF Cronenberg"
        "Hybrid" -> "\uD83D\uDC7E Hybrid"
        else -> "\uD83D\uDE3A Animal"
    }
}

fun String.genderState(): String {
    return when (this) {
        "Male" -> "\uD83D\uDC66 Male"
        "Female" -> "\uD83D\uDC69 Female"
        "Genderless" -> "\uD83D\uDC35 Genderless"
        else -> "\uD83E\uDD37\u200D♂\uFE0F Unknown"
    }
}

fun String.getLocation(): String = "\uD83D\uDCCC Location: $this"

fun String.getOrigin(): String = "\uD83D\uDC64 Origin: $this"