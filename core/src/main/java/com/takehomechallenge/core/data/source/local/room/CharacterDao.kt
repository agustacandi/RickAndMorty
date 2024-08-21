package com.takehomechallenge.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.takehomechallenge.core.data.source.local.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM character WHERE id = :id")
    fun getCharacterById(id: Int): CharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterEntity)

    @Query("DELETE FROM character WHERE id = :id")
    suspend fun deleteCharacterById(id: Int)

    @Query("DELETE FROM character")
    suspend fun deleteAllCharacters()

}