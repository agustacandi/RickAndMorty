package com.takehomechallenge.candi.di

import com.takehomechallenge.candi.presentation.detail.DetailViewModel
import com.takehomechallenge.candi.presentation.favorite.FavoriteViewModel
import com.takehomechallenge.candi.presentation.home.HomeViewModel
import com.takehomechallenge.candi.presentation.search.SearchViewModel
import com.takehomechallenge.core.domain.character.usecase.CharacterInteractor
import com.takehomechallenge.core.domain.character.usecase.CharacterUseCase
import com.takehomechallenge.core.domain.favorite.usecase.FavoriteInteractor
import com.takehomechallenge.core.domain.favorite.usecase.FavoriteUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CharacterUseCase> { CharacterInteractor(get()) }
    factory<FavoriteUseCase> { FavoriteInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get()) }
}