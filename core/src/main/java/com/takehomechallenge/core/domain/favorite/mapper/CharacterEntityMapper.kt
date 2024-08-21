package com.takehomechallenge.core.domain.favorite.mapper

import com.takehomechallenge.core.data.source.local.entity.CharacterEntity
import com.takehomechallenge.core.domain.character.model.Character

fun List<CharacterEntity>.toDomain(): List<Character> {
    return this.map {
        Character(
            id = it.id,
            name = it.name,
            image = it.image,
        )
    }
}

fun Character.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = this.id ?: 0,
        name = this.name ?: "",
        image = this.image ?: "",
    )
}