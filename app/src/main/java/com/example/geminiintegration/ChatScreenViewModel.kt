package com.example.geminiintegration

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

class ChatScreenViewModel : ViewModel() {

    val generativeModel = GenerativeModel(
        modelName = "gemini-pro", //gemini vision da olabilir.
        apiKey = HomeViewModel.geminiApiKey
    )

    init {
        startChat()
    }

    var aiChat: Chat? = null

    var answerList = mutableStateListOf<Message>()

    val messageText = mutableStateOf("")

    fun onMessageTextChagned(text: String) {
        messageText.value = text
    }

    private fun startChat() {
        aiChat = generativeModel.startChat()
    }

    fun chatGemini() {
        if (messageText.value.isEmpty()) return
        viewModelScope.launch {
            answerList.add(Message(Type.USER, messageText.value))
            val response = aiChat?.sendMessage(messageText.value)
            answerList.add(Message(Type.AI, response?.text ?: "null dondu ai"))
            messageText.value = ""
        }
    }
}


data class Message(
    val type: Type,
    val message: String
)

enum class Type {
    AI,
    USER
}