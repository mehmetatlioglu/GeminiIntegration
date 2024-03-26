package com.example.geminiintegration.network

import com.example.geminiintegration.model.data.CharacterList
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}


object ApiClient {
    val apiService: RickAndMortyApiService by lazy {
        RetrofitClient.retrofit.create(RickAndMortyApiService::class.java)
    }
}

