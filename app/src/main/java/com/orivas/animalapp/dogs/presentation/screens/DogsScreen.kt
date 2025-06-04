package com.orivas.animalapp.dogs.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.orivas.dogs.R
import com.orivas.animalapp.dogs.presentation.viewmodel.DogsViewModel
import com.orivas.animalapp.ui.UiState
import com.orivas.animalapp.ui.components.AnimalCardItem
import com.orivas.animalapp.ui.components.CircularProgress
import com.orivas.animalapp.ui.components.EmptyData
import com.orivas.animalapp.ui.components.ShowMessage
import com.orivas.dogs.domain.model.DogDto
import com.orivas.dogs.domain.model.DogsDto

@Composable
fun DogsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    dogsViewModel: DogsViewModel
) {
    val uiState = dogsViewModel.uiState.collectAsState()

    when (uiState.value) {
        is UiState.Loading -> {
            CircularProgress(
                modifier = modifier,
            )
        }

        is UiState.Success -> {
            ListAnimals(
                modifier = modifier,
                animals = (uiState.value as UiState.Success<DogsDto>).data.dogs
            )
            ShowMessage(
                stringResource(R.string.data_from).format(
                    (uiState.value as UiState.Success<DogsDto>).data.dataFrom.name
                )
            )
        }

        is UiState.Error -> {
            EmptyData(modifier = modifier)
        }

        UiState.None -> { /*Nothing to do*/
        }
    }
}

@Composable
fun ListAnimals(
    modifier: Modifier = Modifier,
    animals: List<DogDto>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = rememberLazyListState()
    ) {
        items(animals) { dog ->
            AnimalCardItem(
                dogName = dog.dogName,
                description = dog.description,
                age = dog.age,
                image = dog.image
            )
        }
    }
}