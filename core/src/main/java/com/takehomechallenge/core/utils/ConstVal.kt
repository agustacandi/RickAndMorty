package com.takehomechallenge.core.utils

import com.takehomechallenge.core.BuildConfig
import com.takehomechallenge.core.di.databaseModule
import com.takehomechallenge.core.di.networkModule
import com.takehomechallenge.core.di.repositoryModule

object ConstVal {
    const val BASE_URL = BuildConfig.BASE_URL
    const val DATABASE_NAME = "character.db"
    const val KEY_CHARACTER_ID = "characterId"

    val coreModules = listOf(networkModule, databaseModule, repositoryModule)
}