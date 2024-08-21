package com.takehomechallenge.core.domain.character.usecase

import com.takehomechallenge.core.data.RemoteResponse
import com.takehomechallenge.core.domain.character.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterUseCase {
    fun getAllCharacters(): Flow<RemoteResponse<List<Character>>>

    fun getCharacterById(id: String): Flow<RemoteResponse<Character>>

    fun getCharacterByName(query: String): Flow<RemoteResponse<List<Character>>>
}