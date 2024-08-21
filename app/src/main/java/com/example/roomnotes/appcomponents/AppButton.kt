package com.example.roomnotes.appcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun AppRadioButton(selected:Boolean=true,color: Color,onClick: () -> Unit) {

    RadioButton(
        modifier = Modifier
            .background(Color.Yellow, CircleShape)
            .size(20.dp),
        selected = !selected,
        onClick = { onClick() },
        colors = RadioButtonDefaults.colors(
            selectedColor = color,
        )
    )

}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewAppRadioButton() {
    AppRadioButton(true,Color.Yellow,onClick = {})
}