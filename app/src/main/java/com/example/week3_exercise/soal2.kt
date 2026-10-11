package com.example.week3_exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Soal2() {
    var koin by remember { mutableStateOf(0) }
    var koinPerKlik by remember { mutableStateOf(1) }
    var hargaUpgrade by remember { mutableStateOf(10) }

    var koinSelanjutnya = (koinPerKlik * 1.5).toInt()
    if (koinSelanjutnya == koinPerKlik) {
        koinSelanjutnya = koinPerKlik + 1
    }

    val interaksiKucing = remember { MutableInteractionSource() }
    val kucingDitekan by interaksiKucing.collectIsPressedAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0x66000000)),
                modifier = Modifier.width(200.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Your Coins", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(koin.toString(), fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color(0xFF00E676))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(koinPerKlik.toString() + " coins per tap", fontSize = 14.sp, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            Text("Tap the Cat!", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(20.dp))

            var gambarKucing = R.drawable.base
            var teksKucing = "Purr~"
            if (kucingDitekan) {
                gambarKucing = R.drawable.click
                teksKucing = "Meow!"
            } else {
                gambarKucing = R.drawable.base
                teksKucing = "Purr~"
            }

            Image(
                painter = painterResource(id = gambarKucing),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(
                        interactionSource = interaksiKucing,
                        indication = null
                    ) {
                        koin = koin + koinPerKlik
                    }
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(teksKucing, fontSize = 20.sp, color = Color.White)

            Spacer(modifier = Modifier.weight(1f))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth().padding(32.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Give Me Your Coin", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Next upgrade: +" + koinSelanjutnya.toString() + " coins per tap", fontSize = 16.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(20.dp))

                    if (koin < hargaUpgrade) {
                        var kurang = hargaUpgrade - koin
                        Button(
                            onClick = { },
                            enabled = false,
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.LightGray)
                        ) {
                            Text("Find " + kurang.toString() + " more coins", color = Color.White, fontSize = 18.sp)
                        }
                    } else {
                        Button(
                            onClick = {
                                koin = koin - hargaUpgrade
                                koinPerKlik = koinSelanjutnya
                                hargaUpgrade = hargaUpgrade * 2
                            },
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                        ) {
                            Text("Pay for " + hargaUpgrade.toString() + " coins", color = Color.White, fontSize = 18.sp)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal2Preview() {
    Soal2()
}