package com.takehomechallenge.core.di

import androidx.room.Room.databaseBuilder
import com.takehomechallenge.core.data.source.local.room.CharacterDatabase
import com.takehomechallenge.core.utils.ConstVal
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        databaseBuilder(
            androidApplication(),
            CharacterDatabase::class.java,
            ConstVal.DATABASE_NAME
        ).build()
    }

    single { get<CharacterDatabase>().characterDao() }
}