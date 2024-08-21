package com.takehomechallenge.core.domain.character.usecase

import com.takehomechallenge.core.domain.character.repository.CharacterRepository

class CharacterInteractor(private val characterRepository: CharacterRepository) : CharacterUseCase {
    override fun getAllCharacters() = characterRepository.getAllCharacters()

    override fun getCharacterById(id: String) = characterRepository.getCharacterById(id)

    override fun getCharacterByName(query: String) = characterRepository.getCharacterByName(query)
}