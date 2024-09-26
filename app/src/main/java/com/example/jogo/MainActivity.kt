package com.example.jogo

import androidx.compose.material3.Button
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jogo.ui.theme.JogoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LayoutMain()
        }
    }
}

@Composable
fun LayoutMain() {
    val players = remember { mutableStateListOf(Jogador(), Jogador(), Jogador(), Jogador(), Jogador(), Jogador()) }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        items(players){player ->
            PlayerCard(player)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PlayerCard(player: Jogador) {
    var name by remember { mutableStateOf(player.nome) }
    var level by remember { mutableStateOf(player.level) }
    var equipmentBonus by remember { mutableStateOf(player.equipmentBonus) }
    var modifiers by remember { mutableStateOf(player.modifiers) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        TextField(value = name, onValueChange = { name = it; player.nome = name }, label = { Text(text = "Nome do Jogador") })

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Level: $level", fontSize = 20.sp, modifier = Modifier.weight(1f))
            Button(onClick = { if (level < 10) { level++; player.level = level } }) { Text("+") }
            Button(onClick = { if (level > 1) { level--; player.level = level } }) { Text("-") }
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Bônus de Equipamento: $equipmentBonus", fontSize = 20.sp, modifier = Modifier.weight(1f))
            Button(onClick = { equipmentBonus++; player.equipmentBonus = equipmentBonus }) { Text("+") }
            Button(onClick = { if (equipmentBonus > 0) { equipmentBonus--; player.equipmentBonus = equipmentBonus } }) { Text("-") }
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Modificadores: $modifiers", fontSize = 20.sp, modifier = Modifier.weight(1f))
            Button(onClick = { modifiers++; player.modifiers = modifiers }) { Text("+") }
            Button(onClick = { if (modifiers > 0) { modifiers--; player.modifiers = modifiers } }) { Text("-") }
        }

        val totalPower = level + equipmentBonus + modifiers
        Text(text = "Poder Total: $totalPower", fontSize = 22.sp)
    }
}