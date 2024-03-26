package com.example.geminiintegration.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RickAndMortyNetworkHandler {
    val okHttpClient = OkHttpClient.Builder()
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.example.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun <T> sendRequest(request: suspend () -> Response<T>): Response<T> {
        lateinit var response: Response<T>
        val job = CoroutineScope(Dispatchers.IO).async {
            try {
                response = request.invoke()
            } catch (e: Exception) {

            }
        }
        job.await()
        return response
    }

}