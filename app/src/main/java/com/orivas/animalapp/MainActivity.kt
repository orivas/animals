package com.orivas.animalapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.orivas.animalapp.dogs.presentation.viewmodel.DogsViewModel
import com.orivas.animalapp.navigation.AnimalAppNavigation
import com.orivas.animalapp.ui.components.AnimalsAppBar
import com.orivas.animalapp.ui.theme.AnimalAppTheme
import dagger.hilt.android.AndroidEntryPoint

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
                    contentColor = Color(0xFFF8F8F8),
                    modifier = Modifier
                        .fillMaxSize(),

                    topBar = {
                        AnimalsAppBar(
                            title = "Dogs We Love",
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