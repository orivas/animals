package com.orivas.animalapp.ui.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.orivas.animalapp.ui.theme.DescriptionColor
import com.orivas.animalapp.ui.theme.TitleColor

@Composable
fun TextTitle(text: String, fontSize: TextUnit = 23.sp){
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize,
        color = TitleColor
    )
}

@Composable
fun TextDescription(text: String, fontSize: TextUnit = 14.sp){
    Text(
        text = text,
        fontSize = fontSize,
        color = DescriptionColor
    )
}