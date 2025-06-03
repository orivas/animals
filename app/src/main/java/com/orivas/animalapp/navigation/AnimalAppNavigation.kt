package com.orivas.animalapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.orivas.animalapp.R
import com.orivas.animalapp.dogs.presentation.screens.DogsScreen
import com.orivas.animalapp.dogs.presentation.viewmodel.DogsViewModel

@Composable
fun AnimalAppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    dogsViewModel: DogsViewModel
) {

    val dogsRoute = stringResource(R.string.dogs_route)

    NavHost(navController = navController, startDestination = dogsRoute) {
        composable(dogsRoute) {
            DogsScreen(
                modifier = modifier,
                navController = navController,
                dogsViewModel = dogsViewModel
            )
        }
    }
}