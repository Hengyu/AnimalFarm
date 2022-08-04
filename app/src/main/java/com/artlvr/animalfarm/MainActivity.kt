package com.artlvr.animalfarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.artlvr.animalfarm.networking.ArtlvrService
import com.artlvr.animalfarm.networking.AsyncAnimalFarmBook
import com.artlvr.animalfarm.poetry.PoetryPage
import com.artlvr.animalfarm.poetry.PoetryViewModel
import com.artlvr.animalfarm.ui.theme.AnimalFarmTheme

class MainActivity : ComponentActivity() {
    private val viewModel: PoetryViewModel = PoetryViewModel(
        poetryProviding = AsyncAnimalFarmBook(service = ArtlvrService.default)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalFarmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PoetryPage(poetry = viewModel.poetry)
                }
            }
        }
        viewModel.fetchPoetry()
    }
}
