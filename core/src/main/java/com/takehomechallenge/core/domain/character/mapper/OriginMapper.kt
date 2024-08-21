package com.takehomechallenge.core.domain.character.mapper

import com.takehomechallenge.core.data.source.remote.response.OriginItem
import com.takehomechallenge.core.domain.character.model.Origin

fun OriginItem.toDomain(): Origin {
    return Origin(
        name = this.name,
        url = this.url
    )
}