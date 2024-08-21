package com.takehomechallenge.core.domain.character.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val image: String? = null,

    val gender: String? = null,

    val species: String? = null,

    val created: String? = null,

    val origin: Origin? = null,

    val name: String? = null,

    val location: Location? = null,

    val episode: List<String?>? = null,

    val id: Int? = null,

    val type: String? = null,

    val url: String? = null,

    val status: String? = null
) : Parcelable
