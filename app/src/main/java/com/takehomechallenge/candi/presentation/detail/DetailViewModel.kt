package com.takehomechallenge.candi.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takehomechallenge.core.data.RemoteResponse
import com.takehomechallenge.core.domain.character.model.Character
import com.takehomechallenge.core.domain.character.usecase.CharacterUseCase
import com.takehomechallenge.core.domain.favorite.usecase.FavoriteUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val characterUseCase: CharacterUseCase,
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    private val _detailCharacter = MutableLiveData<RemoteResponse<Character>>()
    val detailCharacter: LiveData<RemoteResponse<Character>> = _detailCharacter

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite


    fun getCharacterById(id: String) {
        viewModelScope.launch {
            characterUseCase.getCharacterById(id).collect {
                _detailCharacter.value = it
            }
        }
    }

    fun checkFavorite(characterId: String) {
        viewModelScope.launch {
            favoriteUseCase.isFavorite(characterId.toInt()).collect {
                _isFavorite.value = it
            }
        }
    }

    fun addFavorite(character: Character) {
        viewModelScope.launch {
            favoriteUseCase.addFavorite(character)
            _isFavorite.value = true
        }
    }

    fun removeFavorite(characterId: String) {
        viewModelScope.launch {
            favoriteUseCase.removeFavorite(characterId.toInt())
            _isFavorite.value = false
        }
    }
}