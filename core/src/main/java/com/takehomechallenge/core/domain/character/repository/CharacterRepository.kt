package com.takehomechallenge.core.domain.character.repository

import com.takehomechallenge.core.data.RemoteResponse
import com.takehomechallenge.core.domain.character.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacters(): Flow<RemoteResponse<List<Character>>>

    fun getCharacterById(id: String): Flow<RemoteResponse<Character>>

    fun getCharacterByName(query: String): Flow<RemoteResponse<List<Character>>>
}