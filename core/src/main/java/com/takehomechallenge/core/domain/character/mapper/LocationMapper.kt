package com.takehomechallenge.core.domain.character.mapper

import com.takehomechallenge.core.data.source.remote.response.LocationItem
import com.takehomechallenge.core.domain.character.model.Location

fun LocationItem.toDomain(): Location {
    return Location(
        name = this.name,
        url = this.url
    )
}