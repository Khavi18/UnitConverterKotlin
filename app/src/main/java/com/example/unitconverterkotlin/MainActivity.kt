package com.example.unitconverterkotlin

import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverterkotlin.ui.theme.UnitConverterKotlinTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    val inputConversionFactor = remember { mutableStateOf(1.00) }
    val outputConversionFactor = remember { mutableStateOf(1.00) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 32.sp,
        color = Color.Blue
        )

    fun convertUnits() {

        // ?: -> elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * inputConversionFactor.value * 100.0 / outputConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center, //Aligns vertically
        horizontalAlignment = Alignment.CenterHorizontally //Aligns horizontally
    ) {
        //Here all UI elements will be stacked on each other
        Text("Unit Converter", style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            //What should happen when value changes
            inputValue = it
            convertUnits()
        },
            label = { Text("Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Here all UI elements will be next to each other
            Box {

                //Dropdown - Input
                Button(onClick = { inputExpanded = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = inputExpanded, onDismissRequest = { inputExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Centimeters"
                            inputConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Meters"
                            inputConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Feet"
                            inputConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            inputExpanded = false
                            inputUnit = "Millimeters"
                            inputConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                //Dropdown - Output
                Button(onClick = { outputExpanded = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = outputExpanded, onDismissRequest = { outputExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Centimeters"
                            outputConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Meters"
                            outputConversionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Feet"
                            outputConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            outputExpanded = false
                            outputUnit = "Millimeters"
                            outputConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue $outputUnit",
                style = MaterialTheme.typography.headlineMedium
            )
    }
}
/* Toast
Toast.makeText(context,
     "Thanks for clicking!",
      Toast.LENGTH_LONG).show()
 */

//Quiz 15
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterKotlinTheme {
        UnitConverter()
    }
}