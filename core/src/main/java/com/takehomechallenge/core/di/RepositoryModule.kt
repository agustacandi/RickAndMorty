package com.takehomechallenge.core.di

import com.takehomechallenge.core.data.source.local.FavoriteRepositoryImpl
import com.takehomechallenge.core.data.source.remote.CharacterRepositoryImpl
import com.takehomechallenge.core.domain.character.repository.CharacterRepository
import com.takehomechallenge.core.domain.favorite.repository.FavoriteRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
}