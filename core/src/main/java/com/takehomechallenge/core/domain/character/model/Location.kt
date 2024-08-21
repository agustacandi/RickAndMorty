package com.takehomechallenge.core.domain.character.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val name: String? = null,

    val url: String? = null
) : Parcelable