package com.orivas.animalapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orivas.animalapp.ui.components.text.TextTitle
import com.orivas.animalapp.ui.theme.TitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalsAppBar(
    title: String,
    onBack: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        navigationIcon = {
            Icon(
                Icons.Filled.ArrowBackIosNew,
                contentDescription = "Arrow back",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(32.dp)
                    .clickable {
                        onBack?.invoke()
                    },
                tint = TitleColor,
            )
        },
        title = {
            TextTitle(title, fontSize = 25.sp)
        }
    )
}