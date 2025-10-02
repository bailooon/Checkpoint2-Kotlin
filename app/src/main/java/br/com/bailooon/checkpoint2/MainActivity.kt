package br.com.bailooon.checkpoint2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.bailooon.checkpoint2.components.GameCard
import br.com.bailooon.checkpoint2.components.StudioCard
import br.com.bailooon.checkpoint2.models.Game
import br.com.bailooon.checkpoint2.repository.getAllGames
import br.com.bailooon.checkpoint2.repository.getGamesByStudio
import br.com.bailooon.checkpoint2.ui.theme.Checkpoint2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Checkpoint2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GamesScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GamesScreen(modifier: Modifier = Modifier) {
    var searchTextState by remember { mutableStateOf("") }
    var gamesListState by remember { mutableStateOf(getAllGames()) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Meus jogos favoritos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = searchTextState,
            onValueChange = { searchTextState = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Nome do estúdio") },
            trailingIcon = {
                IconButton(onClick = { gamesListState = getGamesByStudio(searchTextState) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(){
            items(gamesListState){
                StudioCard(game = it)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn() {
            items(gamesListState) {
                GameCard(game = it)
            }
        }
    }
}

@Preview(showBackground = true, name = "Games Screen Preview")
@Composable
fun PreviewGamesScreen() {
    Checkpoint2Theme {
        GamesScreen()
    }
}

@Preview(showBackground = true, name = "Studio Card Preview")
@Composable
fun PreviewStudioCard() {
    Checkpoint2Theme {
        StudioCard(game = Game(1, "Example Game", "Example Studio", 2023))
    }
}

@Preview(showBackground = true, name = "Game Card Preview")
@Composable
fun PreviewGameCard() {
    Checkpoint2Theme {
        GameCard(game = Game(1, "Example Game", "Example Studio", 2023))
    }
}