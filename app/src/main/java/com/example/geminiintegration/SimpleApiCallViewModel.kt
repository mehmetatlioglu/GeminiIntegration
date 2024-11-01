package com.example.geminiintegration

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geminiintegration.model.data.Character
import com.example.geminiintegration.model.data.CharacterList
import com.example.geminiintegration.model.data.Location
import com.example.geminiintegration.model.data.LocationItem
import com.example.geminiintegration.model.data.LocationList
import com.example.geminiintegration.network.ApiClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SimpleApiCallViewModel() : ViewModel() {

    var characters = mutableStateListOf<Character>()
        private set

    var locations = mutableStateListOf<LocationItem>()
        private set

    init {
        getCharacterList()
        getLocationList()
    }

    private fun getCharacterList() {

        val call = ApiClient.apiService.characters()

        call.enqueue(object : Callback<CharacterList> {
            override fun onResponse(call: Call<CharacterList>, response: Response<CharacterList>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    result?.results?.let {
                        characters.addAll(it)
                    }
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<CharacterList>, t: Throwable) {
                // Handle failure
            }
        })


    }

    private fun getLocationList() {

        val call = ApiClient.apiService.locations()

        call.enqueue(object : Callback<LocationList> {
            override fun onResponse(call: Call<LocationList>, response: Response<LocationList>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    result?.results?.let {
                        locations.addAll(it)
                        result.results.map {
                            Log.d("locationResultAquired", it.name)
                        }
                    }

                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<LocationList>, t: Throwable) {
                // Handle failure
            }
        })


    }

}