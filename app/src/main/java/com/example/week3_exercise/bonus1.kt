package com.example.week3_exercise

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Remove
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
import java.text.NumberFormat
import java.util.Locale

@Composable
fun Bonus1(modifier: Modifier = Modifier) {
    var kuantitas by remember { mutableStateOf(1) }
    var ukuran by remember { mutableStateOf("Regular") }
    
    var hargaSatuan = 25000
    if (ukuran == "Large") {
        hargaSatuan = 25000 + 6000
    }

    var subtotal = hargaSatuan * kuantitas
    var pajak = subtotal * 10 / 100
    var total = subtotal + pajak

    var konteks = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Kopi Kenangan Senja",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4037)
            )
            Icon(
                imageVector = Icons.Default.LocalCafe,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFFFCC80)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color(0xFF795548), shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Coffee,
                        contentDescription = null,
                        tint = Color(0xFFFFCC80),
                        modifier = Modifier.size(80.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Caramel Latte",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3E2723)
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Espresso shot, steamed milk & caramel syrup",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                var formatUang = NumberFormat.getNumberInstance(Locale("id", "ID")).format(25000)
                var teksHarga = "Rp " + formatUang + " / cup"
                Text(
                    text = teksHarga,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD84315)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "PILIHAN UKURAN:",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037)
        )
        
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            var warnaTombolReg = Color.White
            var warnaTeksReg = Color.Gray
            var warnaGarisReg = Color.LightGray
            if (ukuran == "Regular") {
                warnaTombolReg = Color.White
                warnaTeksReg = Color(0xFFD84315)
                warnaGarisReg = Color(0xFFD84315)
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(warnaTombolReg, shape = RoundedCornerShape(8.dp))
                    .border(BorderStroke(1.dp, warnaGarisReg), shape = RoundedCornerShape(8.dp))
                    .clickable { ukuran = "Regular" }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Regular (+0)", color = warnaTeksReg, fontSize = 14.sp)
            }

            var warnaTombolLarge = Color.White
            var warnaTeksLarge = Color.Gray
            var warnaGarisLarge = Color.LightGray
            if (ukuran == "Large") {
                warnaTombolLarge = Color.White
                warnaTeksLarge = Color(0xFFD84315)
                warnaGarisLarge = Color(0xFFD84315)
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(warnaTombolLarge, shape = RoundedCornerShape(8.dp))
                    .border(BorderStroke(1.dp, warnaGarisLarge), shape = RoundedCornerShape(8.dp))
                    .clickable { ukuran = "Large" }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Large (+6k)", color = warnaTeksLarge, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color(0xFFFFCC80)), shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Jumlah Pesanan:",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E2723)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                        .clickable {
                            if (kuantitas > 1) {
                                kuantitas = kuantitas - 1
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Remove, contentDescription = null, tint = Color.Gray)
                }

                Spacer(modifier = Modifier.width(16.dp))
                Text(text = kuantitas.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color(0xFFFFE0B2), RoundedCornerShape(4.dp))
                        .clickable {
                            kuantitas = kuantitas + 1
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color(0xFFD84315))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color(0xFFFFCC80)), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Subtotal:", color = Color.Gray, fontSize = 14.sp)
                var teksSubtotal = "Rp " + NumberFormat.getNumberInstance(Locale("id", "ID")).format(subtotal)
                Text(text = teksSubtotal, color = Color.Gray, fontSize = 14.sp)
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Pajak Resto (10%):", color = Color.Gray, fontSize = 14.sp)
                var teksPajak = "Rp " + NumberFormat.getNumberInstance(Locale("id", "ID")).format(pajak)
                Text(text = teksPajak, color = Color.Gray, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color(0xFFFFCC80), thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Total Tagihan:", fontWeight = FontWeight.Bold, color = Color(0xFF3E2723), fontSize = 16.sp)
                var teksTotal = "Rp " + NumberFormat.getNumberInstance(Locale("id", "ID")).format(total)
                Text(text = teksTotal, fontWeight = FontWeight.Bold, color = Color(0xFFD84315), fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                var pesan = "Pesanan " + kuantitas.toString() + " Caramel Latte ditambahkan. Total: Rp " + NumberFormat.getNumberInstance(Locale("id", "ID")).format(total)
                Toast.makeText(konteks, pesan, Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD84315)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "TAMBAH KE KERANJANG", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Bonus1Preview() {
    Bonus1()
}