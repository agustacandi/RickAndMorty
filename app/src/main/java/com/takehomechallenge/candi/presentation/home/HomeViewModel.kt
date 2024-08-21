package com.takehomechallenge.candi.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.takehomechallenge.core.domain.character.usecase.CharacterUseCase

class HomeViewModel(characterUseCase: CharacterUseCase) : ViewModel() {
    val characters = characterUseCase.getAllCharacters().asLiveData()
}