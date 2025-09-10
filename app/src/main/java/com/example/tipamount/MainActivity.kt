package com.example.tipamount

import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipamount.ui.theme.TipAmountTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {  }
            TipAmountTheme {
                    TipAmount()
            }
        }
    }
}

@Composable
fun TipAmount(modifier: Modifier = Modifier) {
    var billAmount by remember { mutableStateOf("") }
    val value = 15.00
    Column(
        modifier = modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = modifier
                .size(400.dp)

        ) {
            Row(modifier = modifier
                .padding(top = 10.dp)) {
                Text(
                    text = "Calculate Tip",
                    modifier = Modifier.padding(start = 15.dp, bottom = 20.dp)
                )
            }
            Spacer(Modifier.size(32.dp))
            Row(modifier = modifier
                .padding(top = 30.dp)) {
                TextField(
                    value = billAmount,
                    onValueChange = { newBillAmount -> billAmount = newBillAmount },
                    label = { Text("Bill Amount") },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Red,
                        unfocusedTextColor = Color.Blue,
                        cursorColor = Color.Green,
                        focusedIndicatorColor = Color.Magenta,
                        unfocusedIndicatorColor = Color.Cyan
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)

                )
            }
            Spacer(Modifier.size(32.dp))
            Row(modifier = modifier
                .padding(top = 120.dp)) {
                Text(
                    text = "Tip amount: $$value",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun TipAmountPreview() {
    TipAmountTheme {
       TipAmount()
    }
}