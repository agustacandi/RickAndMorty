package com.takehomechallenge.core.domain.favorite.usecase

import com.takehomechallenge.core.domain.character.model.Character
import com.takehomechallenge.core.domain.favorite.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class FavoriteInteractor(private val favoriteRepository: FavoriteRepository) : FavoriteUseCase {
    override suspend fun addFavorite(character: Character) =
        favoriteRepository.addFavorite(character)

    override fun getAllFavorite(): Flow<List<Character>> = favoriteRepository.getAllFavorite()

    override fun isFavorite(id: Int): Flow<Boolean> = favoriteRepository.isFavorite(id)

    override suspend fun removeFavorite(id: Int) = favoriteRepository.removeFavorite(id)

    override suspend fun removeAllFavorite() = favoriteRepository.removeAllFavorite()
}