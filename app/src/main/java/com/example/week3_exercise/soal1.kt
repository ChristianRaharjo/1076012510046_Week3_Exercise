package com.example.week3_exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class LayarGame {
    AWAL, NUNGGU, HIJAU_KLIK, HASIL_TRIAL, GAGAL, HASIL_AKHIR
}

@Composable
fun TrialResultsCard(trial1: Long, trial2: Long, trial3: Long, rataRata: Long? = null) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.padding(16.dp).width(200.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Trial Results", color = Color.Blue, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("1", color = Color.Green, fontWeight = FontWeight.Bold)
                    if (trial1 > 0) {
                        Text(trial1.toString() + "ms", fontSize = 12.sp)
                    } else {
                        Text("-", fontSize = 12.sp)
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("2", color = Color.Green, fontWeight = FontWeight.Bold)
                    if (trial2 > 0) {
                        Text(trial2.toString() + "ms", fontSize = 12.sp)
                    } else {
                        Text("-", fontSize = 12.sp)
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("3", color = Color.Green, fontWeight = FontWeight.Bold)
                    if (trial3 > 0) {
                        Text(trial3.toString() + "ms", fontSize = 12.sp)
                    } else {
                        Text("-", fontSize = 12.sp)
                    }
                }
            }

            if (rataRata != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Average Score", color = Color.Blue, fontSize = 12.sp)
                Text(
                    text = rataRata.toString() + "ms",
                    color = Color(0xFFFF9800),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun Soal1(modifier: Modifier = Modifier) {
    var layar by remember { mutableStateOf(LayarGame.AWAL) }

    var waktuMulai by remember { mutableStateOf(0L) }
    var waktuKlik by remember { mutableStateOf(0L) }

    var trial1 by remember { mutableStateOf(0L) }
    var trial2 by remember { mutableStateOf(0L) }
    var trial3 by remember { mutableStateOf(0L) }

    var jumlahTrialSelesai by remember { mutableStateOf(0) }

    val scope = rememberCoroutineScope()

    fun mulaiNunggu() {
        layar = LayarGame.NUNGGU
        scope.launch {
            val angkaRandom = (500..4500).random().toLong()
            delay(angkaRandom)
            if (layar == LayarGame.NUNGGU) {
                layar = LayarGame.HIJAU_KLIK
                waktuMulai = System.currentTimeMillis()
            }
        }
    }

    var warnaBackground = Color.Cyan
    if (layar == LayarGame.AWAL) {
        warnaBackground = Color.Cyan
    } else if (layar == LayarGame.NUNGGU) {
        warnaBackground = Color(0xFFE53935)
    } else if (layar == LayarGame.HIJAU_KLIK) {
        warnaBackground = Color(0xFF4CAF50)
    } else if (layar == LayarGame.GAGAL) {
        warnaBackground = Color.Red
    } else if (layar == LayarGame.HASIL_TRIAL) {
        warnaBackground = Color(0xFF4CAF50)
    } else if (layar == LayarGame.HASIL_AKHIR) {
        var rataRata = (trial1 + trial2 + trial3) / 3
        if (rataRata < 180) {
            warnaBackground = Color(0xFF00E676)
        } else if (rataRata < 280) {
            warnaBackground = Color(0xFF2196F3)
        } else if (rataRata < 450) {
            warnaBackground = Color(0xFFFF9800)
        } else {
            warnaBackground = Color(0xFFFF5722)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(warnaBackground)
            .clickable {
                if (layar == LayarGame.AWAL) {
                    mulaiNunggu()
                } else if (layar == LayarGame.NUNGGU) {
                    layar = LayarGame.GAGAL
                } else if (layar == LayarGame.HIJAU_KLIK) {
                    waktuKlik = System.currentTimeMillis() - waktuMulai
                    jumlahTrialSelesai = jumlahTrialSelesai + 1

                    if (jumlahTrialSelesai == 1) {
                        trial1 = waktuKlik
                        layar = LayarGame.HASIL_TRIAL
                    } else if (jumlahTrialSelesai == 2) {
                        trial2 = waktuKlik
                        layar = LayarGame.HASIL_TRIAL
                    } else if (jumlahTrialSelesai == 3) {
                        trial3 = waktuKlik
                        layar = LayarGame.HASIL_AKHIR
                    }
                } else if (layar == LayarGame.HASIL_TRIAL) {
                    mulaiNunggu()
                } else if (layar == LayarGame.GAGAL) {
                    layar = LayarGame.AWAL
                } else if (layar == LayarGame.HASIL_AKHIR) {
                    jumlahTrialSelesai = 0
                    trial1 = 0L
                    trial2 = 0L
                    trial3 = 0L
                    layar = LayarGame.AWAL
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (layar == LayarGame.AWAL) {
                Text("Reaction", fontSize = 40.sp, color = Color.White)
                Icon(
                    imageVector = Icons.Default.FlashOn,
                    contentDescription = null,
                    modifier = Modifier.size(150.dp),
                    tint = Color.White
                )
                Text("Test", fontSize = 30.sp, color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Click to Start", fontSize = 20.sp, color = Color.White)
                
                if (jumlahTrialSelesai > 0) {
                    Spacer(modifier = Modifier.height(30.dp))
                    TrialResultsCard(trial1 = trial1, trial2 = trial2, trial3 = trial3)
                }
            } else if (layar == LayarGame.NUNGGU) {
                Text("Get Ready", fontSize = 30.sp, color = Color.White)
                Text("Wait for green light...", fontSize = 20.sp, color = Color.White)
            } else if (layar == LayarGame.HIJAU_KLIK) {
                Text("GO!", fontSize = 50.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(20.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.DirectionsRun,
                    contentDescription = "Run Icon",
                    modifier = Modifier.size(120.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text("CLICK NOW!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Text("TAP AS FAST AS YOU CAN", fontSize = 16.sp, color = Color.White)
            } else if (layar == LayarGame.GAGAL) {
                Text("FAIL!", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(30.dp))
                Icon(
                    imageVector = Icons.Default.ThumbDown,
                    contentDescription = "Thumb Down",
                    modifier = Modifier.size(120.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    "You clicked too early, TRY TO\nREAD THE RULE BRO", 
                    fontSize = 18.sp, 
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text("TRY AGAIN", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                
                if (jumlahTrialSelesai > 0) {
                    Spacer(modifier = Modifier.height(40.dp))
                    TrialResultsCard(trial1 = trial1, trial2 = trial2, trial3 = trial3)
                }
            } else if (layar == LayarGame.HASIL_TRIAL) {
                Text("Trial " + jumlahTrialSelesai.toString() + " Complete!", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.White, shape = RoundedCornerShape(50.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Check Icon",
                        modifier = Modifier.size(80.dp),
                        tint = Color(0xFF4CAF50)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Text("Time: " + waktuKlik.toString() + "ms", fontSize = 20.sp, color = Color.White)
                Spacer(modifier = Modifier.height(20.dp))
                var teksLanjut = jumlahTrialSelesai + 1
                Text("Continue to Trial " + teksLanjut.toString(), fontSize = 16.sp, color = Color.White)
                Spacer(modifier = Modifier.height(40.dp))
                TrialResultsCard(trial1 = trial1, trial2 = trial2, trial3 = trial3)
            } else if (layar == LayarGame.HASIL_AKHIR) {
                var rataRata = (trial1 + trial2 + trial3) / 3
                
                var kategori = ""
                if (rataRata < 180) {
                    kategori = "DANG YOU ARE SO FAST BRO!"
                } else if (rataRata < 280) {
                    kategori = "YOUR REFLEX IS GOOD"
                } else if (rataRata < 450) {
                    kategori = "MEH LIKE OTHER PERSON"
                } else {
                    kategori = "YOU LIKE A SNAIL BRO"
                }

                var gambarHasil = R.drawable.fsat
                if (rataRata < 180) {
                    gambarHasil = R.drawable.fsat
                } else if (rataRata < 280) {
                    gambarHasil = R.drawable.good
                } else if (rataRata < 450) {
                    gambarHasil = R.drawable.llike_other_person
                } else {
                    gambarHasil = R.drawable.snail
                }

                Text(
                    text = kategori, 
                    fontSize = 30.sp, 
                    fontWeight = FontWeight.Bold, 
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.height(40.dp))
                Image(
                    painter = painterResource(id = gambarHasil),
                    contentDescription = "Result Image",
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))

                Text("Average: " + rataRata.toString() + "ms", fontSize = 24.sp, color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Click to Start New Test", fontSize = 16.sp, color = Color.White)
                
                Spacer(modifier = Modifier.height(30.dp))
                TrialResultsCard(trial1 = trial1, trial2 = trial2, trial3 = trial3, rataRata = rataRata)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal1Preview() {
    Soal1()
}