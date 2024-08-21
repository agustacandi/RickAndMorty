package com.takehomechallenge.candi.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takehomechallenge.core.data.RemoteResponse
import com.takehomechallenge.core.domain.character.model.Character
import com.takehomechallenge.core.domain.character.usecase.CharacterUseCase
import kotlinx.coroutines.launch

class SearchViewModel(private val characterUseCase: CharacterUseCase) : ViewModel() {
    private val _searchResult = MutableLiveData<RemoteResponse<List<Character>>>()
    val searchResult: LiveData<RemoteResponse<List<Character>>> = _searchResult

    fun getCharacterByName(query: String) {
        viewModelScope.launch {
            characterUseCase.getCharacterByName(query).collect {
                _searchResult.value = it
            }
        }
    }
}