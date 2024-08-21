package com.takehomechallenge.core.domain.character.mapper

import com.takehomechallenge.core.data.source.remote.response.CharacterItem
import com.takehomechallenge.core.domain.character.model.Character

fun CharacterItem.toDomain(): Character {
    return Character(
        id = this.id,
        name = this.name,
        image = this.image,
        species = this.species,
        status = this.status,
        gender = this.gender,
        created = this.created,
        origin = this.origin?.toDomain(),
        location = this.location?.toDomain(),
        episode = this.episode,
        type = this.type,
        url = this.url
    )
}

fun List<CharacterItem>.toDomain(): List<Character> {
    return this.map {
        it.toDomain()
    }
}