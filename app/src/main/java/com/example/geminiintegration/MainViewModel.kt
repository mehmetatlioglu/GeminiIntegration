package com.example.geminiintegration

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var selectedScreen = mutableStateOf<Screens?>(null)
        private set

    fun onButtonClicked(type: Screens?) {
        selectedScreen.value = type
    }


    enum class Screens {
        PICK_IMAGE,
        COMPARE_IMAGE,
        CHAT
    }

}