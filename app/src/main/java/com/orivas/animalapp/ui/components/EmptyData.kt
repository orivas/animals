package com.orivas.animalapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orivas.dogs.R
import com.orivas.animalapp.ui.components.text.TextDescription

@Composable
fun EmptyData(
    modifier: Modifier,
    message: String = stringResource(R.string.no_data)
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextDescription(message)
    }
}