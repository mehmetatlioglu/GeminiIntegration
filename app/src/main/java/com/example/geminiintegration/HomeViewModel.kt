package com.example.geminiintegration

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


    var selectedImageUri = mutableStateOf<Uri?>(null)
        private set
    var selectedImageBitmap: Bitmap? = null
        private set

    val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash", //gemini vision da olabilir.
        apiKey = geminiApiKey
    )

    var suggestionText = mutableStateOf("")
        private set

    val contentText = mutableStateOf("")

    fun onUriSelection(uri: Uri?) {
        selectedImageUri.value = uri
    }

    fun setSelectedImageBitmapData(bitmap: Bitmap?) {
        selectedImageBitmap = bitmap
    }

    fun onSuggestionTextChanged(text: String) {
        suggestionText.value = text
    }

    fun onSuggestionClicked() {
        Log.d("generativeModelrequest","onSuggestionClicked")
        if (selectedImageUri.value == null) return
        viewModelScope.launch {
            val response = generativeModel.generateContent(
                content {
                    image(selectedImageBitmap ?: return@content)
                    text(suggestionText.value)
                }
            )
            Log.d("generativeModelrequest","result")
            contentText.value = response.text ?: "Ai coktu hata verdi bir seyler"
        }.invokeOnCompletion {
            Log.d("generativeModelrequest","suggestion invokeOnCompletion")
        }
    }


    companion object {
        const val geminiApiKey = "AIzaSyD3EmhMmy6ty-acuePUYEuqWPhib3cd9uo"
    }
}