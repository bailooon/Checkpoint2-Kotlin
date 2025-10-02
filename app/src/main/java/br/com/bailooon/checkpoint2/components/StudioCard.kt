package br.com.bailooon.checkpoint2.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bailooon.checkpoint2.models.Game
import br.com.bailooon.checkpoint2.ui.theme.Checkpoint2Theme

@Composable
fun StudioCard(game: Game) {
    Card(modifier = Modifier
        .size(100.dp)
        .padding(end = 4.dp)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = game.studio)
        }
    }
}

@Preview(showBackground = true, name = "Studio Card Preview")
@Composable
fun PreviewStudioCard() {
    Checkpoint2Theme {
        StudioCard(game = Game(1, "Example Game", "Example Studio", 2023))
    }
}