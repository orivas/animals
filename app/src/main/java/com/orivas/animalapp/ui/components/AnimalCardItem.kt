package com.orivas.animalapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.orivas.animalapp.ui.components.text.TextDescription
import com.orivas.animalapp.ui.components.text.TextTitle

@Composable
fun AnimalCardItem(
    dogName: String,
    description: String,
    age: String,
    image: String? = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        AsyncImage(
            modifier = Modifier
                .height(200.dp)
                .width(140.dp)
                .clip(CircleShape.copy(all = CornerSize(15.dp))),
            contentScale = ContentScale.Crop,
            model = image,
            contentDescription = "Animal image"
        )
        Column(
            Modifier
                .height(170.dp)
                .fillMaxWidth()
                .background(
                    Color.White, shape = RoundedCornerShape
                        (
                        bottomEnd = 15.dp,
                        topEnd = 15.dp
                    )
                )
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TextTitle(text = dogName)
            Spacer(Modifier.height(10.dp))
            TextDescription(text = description)
            Spacer(Modifier.height(10.dp))
            TextTitle(text = age, fontSize = 14.sp)
        }
    }
}