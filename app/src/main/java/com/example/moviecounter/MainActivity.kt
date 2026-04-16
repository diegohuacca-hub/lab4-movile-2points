package com.example.moviecounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieCounter()
        }
    }
}

@Composable
fun MovieCounter(modifier: Modifier = Modifier) {

    // ✅ Estados con persistencia
    var count by rememberSaveable { mutableStateOf(0) }
    var movieName by rememberSaveable { mutableStateOf("") }
    var lastMovie by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 📊 Contador
        Text(
            text = "You have added $count movies.",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 📝 Campo de texto
        TextField(
            value = movieName,
            onValueChange = { movieName = it },
            label = { Text("Movie Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔘 Botón
        Button(
            onClick = {
                if (movieName.isNotBlank()) {
                    count++
                    lastMovie = movieName
                    movieName = ""
                }
            }
        ) {
            Text("Add Movie")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🎬 Última película agregada
        if (lastMovie.isNotEmpty()) {
            Text(
                text = "Last movie added: $lastMovie",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMovieCounter() {
    MovieCounter()
}