package com.example.geminiintegration

import android.provider.CalendarContract
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size.Companion.ORIGINAL
import com.example.geminiintegration.model.data.Character

@Composable
internal fun SimpleApiCallScreen(viewModel: SimpleApiCallViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Adaptive(128.dp)
    ) {
        itemsIndexed(viewModel.characters) { index, item ->
            RickAndMortyItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), character = item
            )
        }
    }
}

@Composable
fun RickAndMortyItem(modifier: Modifier, character: Character) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(character.image)
            .size(ORIGINAL)
            .build(),
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, Color.White, shape = RoundedCornerShape(16.dp))
            .paint(painter)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
        ) {
            Text(text = character.name, style = LocalTextStyle.current.copy(color = Color.White, fontSize = 12.sp))
        }
    }
}