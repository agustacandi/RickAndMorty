package com.takehomechallenge.core.data.source.remote.response

import com.squareup.moshi.Json

data class CharacterResponse(

    @Json(name = "results")
    val results: List<CharacterItem>? = null,

    @Json(name = "info")
    val info: InfoItem? = null
)

data class OriginItem(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "url")
    val url: String? = null
)

data class CharacterItem(

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "gender")
    val gender: String? = null,

    @Json(name = "species")
    val species: String? = null,

    @Json(name = "created")
    val created: String? = null,

    @Json(name = "origin")
    val origin: OriginItem? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "location")
    val location: LocationItem? = null,

    @Json(name = "episode")
    val episode: List<String>? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "type")
    val type: String? = null,

    @Json(name = "url")
    val url: String? = null,

    @Json(name = "status")
    val status: String? = null
)

data class LocationItem(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "url")
    val url: String? = null
)

data class InfoItem(

    @Json(name = "next")
    val next: String? = null,

    @Json(name = "pages")
    val pages: Int? = null,

    @Json(name = "prev")
    val prev: Any? = null,

    @Json(name = "count")
    val count: Int? = null
)
