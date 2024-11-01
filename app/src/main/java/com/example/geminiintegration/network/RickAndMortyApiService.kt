package com.example.geminiintegration.network

import com.example.geminiintegration.model.data.CharacterList
import com.example.geminiintegration.model.data.LocationList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApiService {
    @GET("character")
    fun characters(): Call<CharacterList>

    @GET("location")
    fun locations(): Call<LocationList>
}