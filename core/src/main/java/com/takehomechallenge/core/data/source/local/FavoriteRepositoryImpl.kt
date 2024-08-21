package com.takehomechallenge.core.data.source.local

import com.takehomechallenge.core.data.source.local.room.CharacterDao
import com.takehomechallenge.core.domain.character.model.Character
import com.takehomechallenge.core.domain.favorite.mapper.toDomain
import com.takehomechallenge.core.domain.favorite.mapper.toEntity
import com.takehomechallenge.core.domain.favorite.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FavoriteRepositoryImpl(private val characterDao: CharacterDao) : FavoriteRepository {
    override suspend fun addFavorite(character: Character) =
        characterDao.insertCharacter(character.toEntity())

    override fun getAllFavorite(): Flow<List<Character>> = flow {
        val characterFavorite = characterDao.getAllCharacters()
        emit(characterFavorite.toDomain())
    }.flowOn(Dispatchers.IO)

    override fun isFavorite(id: Int): Flow<Boolean> = flow {
        val characterFavorite = characterDao.getCharacterById(id)
        emit(characterFavorite != null)
    }.flowOn(Dispatchers.IO)

    override suspend fun removeFavorite(id: Int) = characterDao.deleteCharacterById(id)

    override suspend fun removeAllFavorite() = characterDao.deleteAllCharacters()
}