package com.example.geminiintegration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(viewModel: ChatScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        LazyColumn {
            items(viewModel.answerList.size) {
                val answer = viewModel.answerList[it]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .background(
                                if (answer.type == Type.AI) Color.Blue.copy(0.5f) else Color.Cyan.copy(0.5f)
                            )
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth(), text = answer.message,
                            textAlign = if (answer.type == Type.AI) TextAlign.Start else TextAlign.End
                        )
                    }
                }
            }
        }
        Column {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.messageText.value,
                onValueChange = viewModel::onMessageTextChagned
            )
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = viewModel::chatGemini
            ) {
                Text(text = "Chat With Gemini")
            }
        }
    }
}