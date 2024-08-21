package com.takehomechallenge.core.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.takehomechallenge.core.BuildConfig
import com.takehomechallenge.core.data.source.remote.network.ApiService
import com.takehomechallenge.core.utils.ConstVal
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Logging Interceptor for debugging purposes (only run in debug mode)
private val loggingInterceptor =
    HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

// Moshi for JSON parsing
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

// Koin module for network dependencies
val networkModule = module {
    // Single instance of OkHttpClient
    single {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    // Single instance of Retrofit
    single {
        Retrofit.Builder()
            .baseUrl(ConstVal.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(get())
            .build()
    }

    // Single instance of MovieService
    single {
        provideApiService(get())
    }
}

// Function to provide MovieService instance
fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)