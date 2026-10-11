package com.example.week3_exercise

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

enum class GameState { START, COUNTDOWN, RUNNING, GAMEOVER }
enum class GameMode { COLOR, TEXT }
enum class ColorName { RED, BLUE, GREEN, ORANGE }

@Composable
fun Soal3(modifier: Modifier = Modifier) {
    var gameState by remember { mutableStateOf(GameState.START) }
    var score by remember { mutableStateOf(0) }
    var strikes by remember { mutableStateOf(0) }
    var bestScore by remember { mutableStateOf(0) }
    
    var currentMode by remember { mutableStateOf(GameMode.COLOR) }
    var wordColor by remember { mutableStateOf(ColorName.RED) }
    var inkColor by remember { mutableStateOf(ColorName.BLUE) }
    var isWordOnLeft by remember { mutableStateOf(true) }
    var timeLeft by remember { mutableStateOf(5) }
    var countdownText by remember { mutableStateOf("3") }

    fun getColorValue(colorName: ColorName): Color {
        if (colorName == ColorName.RED) return Color.Red
        if (colorName == ColorName.BLUE) return Color.Blue
        if (colorName == ColorName.GREEN) return Color.Green
        return Color(0xFFFFA500)
    }

    fun getColorString(colorName: ColorName): String {
        if (colorName == ColorName.RED) return "RED"
        if (colorName == ColorName.BLUE) return "BLUE"
        if (colorName == ColorName.GREEN) return "GREEN"
        return "ORANGE"
    }

    fun generateQuestion() {
        var modeAngka = Random.nextInt(2)
        if (modeAngka == 0) {
            currentMode = GameMode.COLOR
        } else {
            currentMode = GameMode.TEXT
        }

        var warna1 = Random.nextInt(4)
        if (warna1 == 0) wordColor = ColorName.RED
        else if (warna1 == 1) wordColor = ColorName.BLUE
        else if (warna1 == 2) wordColor = ColorName.GREEN
        else if (warna1 == 3) wordColor = ColorName.ORANGE

        var warna2 = Random.nextInt(4)
        while (warna2 == warna1) {
            warna2 = Random.nextInt(4)
        }
        
        if (warna2 == 0) inkColor = ColorName.RED
        else if (warna2 == 1) inkColor = ColorName.BLUE
        else if (warna2 == 2) inkColor = ColorName.GREEN
        else if (warna2 == 3) inkColor = ColorName.ORANGE

        var acakPosisi = Random.nextInt(2)
        if (acakPosisi == 0) {
            isWordOnLeft = true
        } else {
            isWordOnLeft = false
        }
        
        timeLeft = 5
    }

    LaunchedEffect(gameState, timeLeft) {
        if (gameState == GameState.RUNNING) {
            if (timeLeft > 0) {
                delay(1000)
                timeLeft = timeLeft - 1
            } else {
                strikes = strikes + 1
                if (strikes >= 3) {
                    if (score > bestScore) {
                        bestScore = score
                    }
                    gameState = GameState.GAMEOVER
                } else {
                    generateQuestion()
                }
            }
        }
    }

    LaunchedEffect(gameState) {
        if (gameState == GameState.COUNTDOWN) {
            countdownText = "3"
            delay(700)
            countdownText = "2"
            delay(700)
            countdownText = "1"
            delay(700)
            countdownText = "Start!"
            delay(700)
            score = 0
            strikes = 0
            generateQuestion()
            gameState = GameState.RUNNING
        }
    }

    fun handleAnswer(selectedColor: ColorName) {
        var jawabanBenar = wordColor
        if (currentMode == GameMode.COLOR) {
            jawabanBenar = inkColor
        }
        
        if (selectedColor == jawabanBenar) {
            score = score + 1
        } else {
            strikes = strikes + 1
        }
        
        if (strikes >= 3) {
            if (score > bestScore) {
                bestScore = score
            }
            gameState = GameState.GAMEOVER
        } else {
            generateQuestion()
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (gameState == GameState.START) {
            Text("Welcome", fontSize = 24.sp)
            Text("to", fontSize = 24.sp)
            Text("Color Word Matching", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { gameState = GameState.COUNTDOWN },
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
            ) {
                Text("Start Game")
            }
        } else if (gameState == GameState.COUNTDOWN) {
            Text(countdownText, fontSize = 48.sp)
        } else if (gameState == GameState.RUNNING) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var modeText = "Mode: TEXT"
                    if (currentMode == GameMode.COLOR) {
                        modeText = "Mode: COLOR"
                    }
                    Text(modeText)
                    
                    var skorText = "v " + score.toString() + "  x " + strikes.toString() + "/3"
                    Text(skorText)
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                var waktuText = timeLeft.toString() + " s"
                Text(waktuText, fontSize = 24.sp)
                Spacer(modifier = Modifier.height(16.dp))
                
                var kataDitampilkan = getColorString(wordColor)
                var warnaTinta = getColorValue(inkColor)
                
                Text(
                    text = kataDitampilkan,
                    color = warnaTinta,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    var leftColor = wordColor
                    var rightColor = inkColor
                    if (isWordOnLeft == false) {
                        leftColor = inkColor
                        rightColor = wordColor
                    }
                    
                    Button(
                        onClick = { handleAnswer(leftColor) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
                    ) {
                        var namaKiri = getColorString(leftColor)
                        Text(namaKiri)
                    }
                    Button(
                        onClick = { handleAnswer(rightColor) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
                    ) {
                        var namaKanan = getColorString(rightColor)
                        Text(namaKanan)
                    }
                }
            }
        } else if (gameState == GameState.GAMEOVER) {
            Text("Game Over!", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text("You're Score", fontSize = 16.sp)
            Text(score.toString(), fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Best Score", fontSize = 16.sp)
            Text(bestScore.toString(), fontSize = 24.sp)
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { gameState = GameState.COUNTDOWN },
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
            ) {
                Text("Restart Game")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { gameState = GameState.START },
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
            ) {
                Text("Exit")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal3Preview() {
    Soal3()
}