package com.takehomechallenge.core.domain.favorite.repository

import com.takehomechallenge.core.domain.character.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun addFavorite(character: Character)
    fun getAllFavorite(): Flow<List<Character>>
    fun isFavorite(id: Int): Flow<Boolean>
    suspend fun removeFavorite(id: Int)
    suspend fun removeAllFavorite()
}