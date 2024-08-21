package com.takehomechallenge.candi.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takehomechallenge.core.domain.character.model.Character
import com.takehomechallenge.core.domain.favorite.usecase.FavoriteUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    private val _favorite = MutableLiveData<List<Character>>()
    val favorite: LiveData<List<Character>> = _favorite

    fun getFavoriteCharacter() {
        viewModelScope.launch {
            favoriteUseCase.getAllFavorite().collect {
                _favorite.value = it
            }
        }
    }

    fun removeAllFavorite() {
        viewModelScope.launch {
            favoriteUseCase.removeAllFavorite()
            getFavoriteCharacter()
        }
    }
}