package com.takehomechallenge.core.data.source.remote.network

import com.takehomechallenge.core.data.source.remote.response.CharacterItem
import com.takehomechallenge.core.data.source.remote.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): CharacterItem

    @GET("character")
    suspend fun getCharacterByName(@Query("name") name: String): CharacterResponse

}