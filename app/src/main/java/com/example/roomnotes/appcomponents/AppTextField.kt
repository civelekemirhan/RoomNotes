package com.example.roomnotes.appcomponents

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.roomnotes.data.NoteEvent

import kotlin.math.max


@Composable
fun AppTextField(
    isOutlinedTextField: Boolean = true,
    borderColor: Color,
    textColor: Color,
    placeholderText: String,
    maxLines: Int = 1,
    textSize:TextUnit,
    content: (text:String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    if (isOutlinedTextField) {

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                content(it)
            },
            textStyle = TextStyle.Default.copy(fontSize = textSize),
            colors= TextFieldDefaults.outlinedTextFieldColors(
                textColor=textColor,
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,

            )
            ,
            placeholder = {
                Text(text = placeholderText, fontSize = textSize, fontFamily = FontFamily.SansSerif)
            },
            maxLines = maxLines,
        )

    } else {
        TextField(value = text,
            onValueChange = {
                text = it
            },
            colors= TextFieldDefaults.textFieldColors(
                textColor=textColor,
                disabledTextColor = textColor,
                focusedIndicatorColor = borderColor,
                unfocusedIndicatorColor = borderColor
            ),
            placeholder = {
                Text(text = placeholderText)
            })
    }

}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewAppTextField() {
    AppTextField(
        isOutlinedTextField = true,
        borderColor = Color.Black,
        textColor = Color.Black,
        placeholderText = "Placeholder",
        textSize = 20.sp){

    }
}