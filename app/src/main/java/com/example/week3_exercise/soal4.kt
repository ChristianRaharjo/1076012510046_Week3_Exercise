package com.example.week3_exercise

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random

enum class Rps { ROCK, PAPER, SCISSOR }
enum class State { INITIAL, PICK, REVEAL, FINISHED }
enum class Result { WIN, LOSE, DRAW }

@Composable
fun Soal4(modifier: Modifier = Modifier) {
    var gameState by rememberSaveable { mutableStateOf(State.INITIAL) }
    var playerScore by rememberSaveable { mutableStateOf(0) }
    var cpuScore by rememberSaveable { mutableStateOf(0) }
    var bestScore by rememberSaveable { mutableStateOf(0) }

    var playerPick by remember { mutableStateOf(Rps.ROCK) }
    var cpuPick by remember { mutableStateOf(Rps.ROCK) }
    var roundResult by remember { mutableStateOf(Result.DRAW) }

    var btn1 by remember { mutableStateOf(Rps.ROCK) }
    var btn2 by remember { mutableStateOf(Rps.PAPER) }
    var btn3 by remember { mutableStateOf(Rps.SCISSOR) }

    fun getEmoji(rps: Rps): String {
        if (rps == Rps.ROCK) return "✊"
        if (rps == Rps.PAPER) return "✋"
        return "✌️"
    }

    fun getNama(rps: Rps): String {
        if (rps == Rps.ROCK) return "Rock"
        if (rps == Rps.PAPER) return "Paper"
        return "Scissor"
    }

    fun acakTombol() {
        var angka = Random.nextInt(6)
        if (angka == 0) {
            btn1 = Rps.ROCK
            btn2 = Rps.PAPER
            btn3 = Rps.SCISSOR
        } else if (angka == 1) {
            btn1 = Rps.ROCK
            btn2 = Rps.SCISSOR
            btn3 = Rps.PAPER
        } else if (angka == 2) {
            btn1 = Rps.PAPER
            btn2 = Rps.ROCK
            btn3 = Rps.SCISSOR
        } else if (angka == 3) {
            btn1 = Rps.PAPER
            btn2 = Rps.SCISSOR
            btn3 = Rps.ROCK
        } else if (angka == 4) {
            btn1 = Rps.SCISSOR
            btn2 = Rps.ROCK
            btn3 = Rps.PAPER
        } else {
            btn1 = Rps.SCISSOR
            btn2 = Rps.PAPER
            btn3 = Rps.ROCK
        }
    }

    fun mainkan(pilihanPemain: Rps) {
        playerPick = pilihanPemain
        var angkaKomputer = Random.nextInt(3)
        if (angkaKomputer == 0) {
            cpuPick = Rps.ROCK
        } else if (angkaKomputer == 1) {
            cpuPick = Rps.PAPER
        } else {
            cpuPick = Rps.SCISSOR
        }

        if (playerPick == cpuPick) {
            roundResult = Result.DRAW
        } else if (playerPick == Rps.ROCK && cpuPick == Rps.SCISSOR) {
            roundResult = Result.WIN
        } else if (playerPick == Rps.PAPER && cpuPick == Rps.ROCK) {
            roundResult = Result.WIN
        } else if (playerPick == Rps.SCISSOR && cpuPick == Rps.PAPER) {
            roundResult = Result.WIN
        } else {
            roundResult = Result.LOSE
        }

        if (roundResult == Result.WIN) {
            playerScore = playerScore + 1
        } else if (roundResult == Result.LOSE) {
            cpuScore = cpuScore + 1
        }

        gameState = State.REVEAL
    }

    LaunchedEffect(gameState) {
        if (gameState == State.REVEAL) {
            delay(700)
            var batasMenang = 5 / 2 + 1
            if (playerScore >= batasMenang || cpuScore >= batasMenang) {
                var selisih = playerScore - cpuScore
                if (selisih > bestScore) {
                    bestScore = selisih
                }
                gameState = State.FINISHED
            } else {
                acakTombol()
                gameState = State.PICK
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var teksSkor = "You " + playerScore.toString() + " - " + cpuScore.toString() + " BOT"
            Text(teksSkor, fontSize = 16.sp)
            Text("Best of 5", fontSize = 16.sp)
        }
        
        Spacer(modifier = Modifier.weight(1f))

        if (gameState == State.INITIAL) {
            Text("Rock • Paper • Scissors", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    playerScore = 0
                    cpuScore = 0
                    acakTombol()
                    gameState = State.PICK
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
            ) {
                Text("Start")
            }
        } else if (gameState == State.PICK) {
            Text("Pick your move!", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(32.dp))
            Text("❔ VS ❔", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(64.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { mainkan(btn1) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
                ) {
                    var teksTombol1 = getEmoji(btn1) + " " + getNama(btn1)
                    Text(teksTombol1)
                }
                Button(
                    onClick = { mainkan(btn2) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
                ) {
                    var teksTombol2 = getEmoji(btn2) + " " + getNama(btn2)
                    Text(teksTombol2)
                }
                Button(
                    onClick = { mainkan(btn3) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
                ) {
                    var teksTombol3 = getEmoji(btn3) + " " + getNama(btn3)
                    Text(teksTombol3)
                }
            }
        } else if (gameState == State.REVEAL) {
            var teksPilihan = getEmoji(playerPick) + " VS " + getEmoji(cpuPick)
            Text(teksPilihan, fontSize = 48.sp)
            Spacer(modifier = Modifier.height(16.dp))
            
            var teksHasil = ""
            if (roundResult == Result.WIN) {
                teksHasil = "You Win!"
            } else if (roundResult == Result.LOSE) {
                teksHasil = "You Lose"
            } else {
                teksHasil = "Draw"
            }
            Text(teksHasil, fontSize = 24.sp)
        } else if (gameState == State.FINISHED) {
            var teksAkhir = ""
            if (playerScore > cpuScore) {
                teksAkhir = "You Win the Match!"
            } else {
                teksAkhir = "You Lose the Match"
            }
            
            Text(teksAkhir, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            var teksBestScore = "Best Score: " + bestScore.toString()
            Text(teksBestScore, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(32.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        playerScore = 0
                        cpuScore = 0
                        acakTombol()
                        gameState = State.PICK
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
                ) {
                    Text("Restart")
                }
                Button(
                    onClick = {
                        playerScore = 0
                        cpuScore = 0
                        gameState = State.INITIAL
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
                ) {
                    Text("Exit")
                }
            }
        }
        
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal4Preview() {
    Soal4()
}