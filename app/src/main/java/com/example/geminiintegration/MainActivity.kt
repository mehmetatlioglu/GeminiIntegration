package com.example.geminiintegration

import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.rememberAsyncImagePainter
import com.example.geminiintegration.ui.theme.GeminiIntegrationTheme
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiIntegrationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    BackHandler {
        viewModel.onButtonClicked(null)
    }
    Column(verticalArrangement = Arrangement.SpaceBetween) {

        Column {
            when (viewModel.selectedScreen.value) {
                MainViewModel.Screens.SIMPLE_API_CALL -> SimpleApiCallScreen()
                MainViewModel.Screens.PICK_IMAGE -> HomeScreen()
                MainViewModel.Screens.COMPARE_IMAGE -> CompareImageScreen()
                MainViewModel.Screens.CHAT -> ChatScreen()
                else -> Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Yeni Başlayanlar için Android")
                }
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .zIndex(12f), horizontalArrangement = Arrangement.Absolute.SpaceAround) {
            /*Button(onClick = {
                viewModel.onButtonClicked(MainViewModel.Screens.SIMPLE_API_CALL)
            }) {
                Text("RiclAnd Morty")
            }*/
            Button(onClick = {
                viewModel.onButtonClicked(MainViewModel.Screens.PICK_IMAGE)
            }) {
                Text("Pick Img")
            }
            Button(onClick = {
                viewModel.onButtonClicked(MainViewModel.Screens.COMPARE_IMAGE)
            }) {
                Text("Compare Img")
            }

            Button(onClick = { viewModel.onButtonClicked(MainViewModel.Screens.CHAT) }) {
                Text("Chat")
            }
        }
    }
}