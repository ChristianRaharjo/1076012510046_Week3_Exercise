package com.example.week3_exercise

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bonus2(modifier: Modifier = Modifier) {
    var spotWisata by remember { mutableStateOf("") }
    var pengalaman by remember { mutableStateOf("") }
    var catatan by remember { mutableStateOf("") }
    var kepuasan by remember { mutableStateOf("Biasa") }
    
    var konteks = LocalContext.current

    var warnaLatarBelakang = Color(0xFF1E212D)
    var warnaTeksUtama = Color.White
    var warnaTeksSekunder = Color.LightGray
    var warnaBanner = Color(0xFF2B5C8F)
    var warnaTextField = Color(0xFF262A38)
    var warnaAksen = Color(0xFF6FB3D2)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(warnaLatarBelakang)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(warnaBanner, shape = RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text("LOG PERJALANAN", color = warnaTeksSekunder, fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Tromso, Norway ", color = warnaTeksUtama, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Icon(
                            imageVector = Icons.Default.AcUnit,
                            contentDescription = "Snowflake",
                            tint = warnaTeksUtama,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Ekspedisi Aurora Borealis", color = warnaTeksSekunder, fontSize = 14.sp)
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text("SPOT WISATA FAVORIT", color = warnaTeksSekunder, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = spotWisata,
                onValueChange = { spotWisata = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Ketik spot wisata...", color = Color.Gray) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = warnaTextField,
                    unfocusedContainerColor = warnaTextField,
                    unfocusedIndicatorColor = Color.DarkGray,
                    focusedIndicatorColor = warnaAksen,
                    focusedTextColor = warnaTeksUtama,
                    unfocusedTextColor = warnaTeksUtama,
                    cursorColor = warnaAksen
                ),
                shape = RoundedCornerShape(12.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text("APA YANG PALING KAMU NIKMATI?", color = warnaTeksSekunder, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = pengalaman,
                onValueChange = { pengalaman = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                placeholder = { Text("Ceritakan pengalamanmu...", color = Color.Gray) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = warnaTextField,
                    unfocusedContainerColor = warnaTextField,
                    unfocusedIndicatorColor = Color.DarkGray,
                    focusedIndicatorColor = warnaAksen,
                    focusedTextColor = warnaTeksUtama,
                    unfocusedTextColor = warnaTeksUtama,
                    cursorColor = warnaAksen
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            
            Text("CATATAN TAMBAHAN / PERLENGKAPAN", color = warnaTeksSekunder, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = catatan,
                onValueChange = { catatan = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("(Ketik perlengkapan ekstra di sini...)", color = Color.Gray) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = warnaTextField,
                    unfocusedContainerColor = warnaTextField,
                    unfocusedIndicatorColor = Color.DarkGray,
                    focusedIndicatorColor = warnaAksen,
                    focusedTextColor = warnaTeksUtama,
                    unfocusedTextColor = warnaTeksUtama,
                    cursorColor = warnaAksen
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
            
            Text("TINGKAT KEPUASAN:", color = warnaTeksSekunder, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                var warnaBgBiasa = Color.Transparent
                var warnaTeksBiasa = warnaTeksSekunder
                var garisBiasa = Color.DarkGray
                if (kepuasan == "Biasa") {
                    warnaBgBiasa = warnaAksen
                    warnaTeksBiasa = Color.Black
                    garisBiasa = warnaAksen
                }
                
                Box(
                    modifier = Modifier
                        .background(warnaBgBiasa, shape = RoundedCornerShape(20.dp))
                        .border(BorderStroke(1.dp, garisBiasa), shape = RoundedCornerShape(20.dp))
                        .clickable { kepuasan = "Biasa" }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    var labelBiasa = "Biasa"
                    if (kepuasan == "Biasa") labelBiasa = "Biasa *"
                    Text(labelBiasa, color = warnaTeksBiasa, fontSize = 14.sp)
                }

                var warnaBgSeru = Color.Transparent
                var warnaTeksSeru = warnaTeksSekunder
                var garisSeru = Color.DarkGray
                if (kepuasan == "Seru") {
                    warnaBgSeru = warnaAksen
                    warnaTeksSeru = Color.Black
                    garisSeru = warnaAksen
                }
                
                Box(
                    modifier = Modifier
                        .background(warnaBgSeru, shape = RoundedCornerShape(20.dp))
                        .border(BorderStroke(1.dp, garisSeru), shape = RoundedCornerShape(20.dp))
                        .clickable { kepuasan = "Seru" }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    var labelSeru = "Seru"
                    if (kepuasan == "Seru") labelSeru = "Seru ★"
                    Text(labelSeru, color = warnaTeksSeru, fontSize = 14.sp)
                }

                var warnaBgLuarBiasa = Color.Transparent
                var warnaTeksLuarBiasa = warnaTeksSekunder
                var garisLuarBiasa = Color.DarkGray
                if (kepuasan == "Luar Biasa") {
                    warnaBgLuarBiasa = warnaAksen
                    warnaTeksLuarBiasa = Color.Black
                    garisLuarBiasa = warnaAksen
                }
                
                Box(
                    modifier = Modifier
                        .background(warnaBgLuarBiasa, shape = RoundedCornerShape(20.dp))
                        .border(BorderStroke(1.dp, garisLuarBiasa), shape = RoundedCornerShape(20.dp))
                        .clickable { kepuasan = "Luar Biasa" }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    var labelLuarBiasa = "Luar Biasa"
                    if (kepuasan == "Luar Biasa") labelLuarBiasa = "Luar Biasa ★"
                    Text(labelLuarBiasa, color = warnaTeksLuarBiasa, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            
            Row {
                Text("Status: ", color = warnaTeksSekunder, fontSize = 14.sp)
                Text("Draft Tersimpan", color = warnaAksen, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
        }

        FloatingActionButton(
            onClick = {
                var namaSpot = spotWisata
                if (namaSpot == "") {
                    namaSpot = "Lokasi belum diisi"
                }
                var pesan = "Jurnal disimpan untuk spot: " + namaSpot
                Toast.makeText(konteks, pesan, Toast.LENGTH_SHORT).show()
            },
            containerColor = warnaAksen,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Simpan", tint = Color.Black)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Bonus2Preview() {
    Bonus2()
}