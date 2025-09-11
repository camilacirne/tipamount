package com.example.tipamount

import android.os.Bundle
import android.os.Message
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipamount.ui.theme.TipAmountTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {  }
            TipAmountTheme {
                    TipAmountLayout()
            }
        }
    }
}
private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String{
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
fun EditNumberField(value: String, onValueChange: (String) -> Unit, text: String, keyboardOptions: KeyboardOptions, modifier: Modifier = Modifier) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Red,
            unfocusedTextColor = Color.Blue,
            cursorColor = Color.Green,
            focusedIndicatorColor = Color.Magenta,
            unfocusedIndicatorColor = Color.Cyan
        )
    )
}


@Composable
fun TipAmountLayout(modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0 //converter String em Double, elvis retorna um valor quando a variavel for nula
    var tipInput by remember { mutableStateOf("") }
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPercent )


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
                EditNumberField(
                    value = tipInput,
                    onValueChange = { tipInput = it },
                    text = "Bill Amount",
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp))
            }
            Spacer(Modifier.size(32.dp))
            Row(modifier = modifier
                .padding(top = 100.dp)) {
                EditNumberField(
                    value = amountInput,
                    onValueChange = { amountInput = it },
                    text = "Tip Percentage",
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp))
            }
            Spacer(Modifier.size(32.dp))
            Row(modifier = modifier
                .padding(top = 180.dp)) {
                Text(
                    text = "Tip amount: $tip",
                    style =  MaterialTheme.typography.displaySmall,
                    modifier = Modifier.fillMaxWidth(),
                    //fontSize = 28.sp,
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
       TipAmountLayout()
    }
}