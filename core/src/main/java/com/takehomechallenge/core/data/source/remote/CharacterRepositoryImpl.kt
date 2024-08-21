package com.takehomechallenge.core.data.source.remote

import com.takehomechallenge.core.data.RemoteResponse
import com.takehomechallenge.core.data.source.remote.network.ApiService
import com.takehomechallenge.core.domain.character.mapper.toDomain
import com.takehomechallenge.core.domain.character.model.Character
import com.takehomechallenge.core.domain.character.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharacterRepositoryImpl(private val apiService: ApiService) : CharacterRepository {
    override fun getAllCharacters(): Flow<RemoteResponse<List<Character>>> = flow {
        try {
            emit(RemoteResponse.Loading)
            val response = apiService.getCharacters()
            val data = response.results
            emit(
                if (data.isNullOrEmpty()) {
                    RemoteResponse.Empty
                } else {
                    RemoteResponse.Success(data.toDomain())
                }
            )
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getCharacterById(id: String): Flow<RemoteResponse<Character>> = flow {
        try {
            emit(RemoteResponse.Loading)
            val response = apiService.getCharacterById(id)
            emit(
                RemoteResponse.Success(response.toDomain())
            )
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getCharacterByName(query: String): Flow<RemoteResponse<List<Character>>> = flow {
        try {
            emit(RemoteResponse.Loading)
            val response = apiService.getCharacterByName(query)
            val data = response.results
            emit(
                if (data.isNullOrEmpty()) {
                    RemoteResponse.Empty
                } else {
                    RemoteResponse.Success(data.toDomain())
                }
            )
        } catch (e: Exception) {
            emit(RemoteResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}