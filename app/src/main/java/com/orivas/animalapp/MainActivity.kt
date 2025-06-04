package com.orivas.animalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.orivas.animalapp.dogs.presentation.viewmodel.DogsViewModel
import com.orivas.animalapp.navigation.AnimalAppNavigation
import com.orivas.animalapp.ui.components.AnimalsAppBar
import com.orivas.animalapp.ui.theme.AnimalAppTheme
import com.orivas.animalapp.ui.theme.BackgroundColor
import dagger.hilt.android.AndroidEntryPoint
import com.orivas.dogs.R

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dogsViewModel: DogsViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            AnimalAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    contentColor = BackgroundColor,
                    modifier = Modifier
                        .fillMaxSize(),

                    topBar = {
                        AnimalsAppBar(
                            title = stringResource(R.string.dogs_screen_title),
                            onBack = {
                                this.finish()
                            }
                        )
                    }
                ) { innerPadding ->
                    AnimalAppNavigation(
                        Modifier.padding(innerPadding),
                        navController = navController,
                        dogsViewModel = dogsViewModel
                    )
                }
            }
        }
    }
}