package com.artlvr.animalfarm

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.artlvr.animalfarm.book.AnimalFarmBook
import com.artlvr.animalfarm.networking.ArtlvrService
import com.artlvr.animalfarm.networking.AsyncAnimalFarmBook
import com.artlvr.animalfarm.poetry.PoetryViewModel
import com.artlvr.animalfarm.poetry.RefreshablePoetryPage
import com.artlvr.animalfarm.ui.theme.AnimalFarmTheme

class MainActivity : ComponentActivity() {
    private val viewModel: PoetryViewModel = PoetryViewModel(
        local = AnimalFarmBook(),
        remote = AsyncAnimalFarmBook(service = ArtlvrService.default)
    )

    private val preferredPoetrySectionKey: String = "preferredPoetrySection"
    private val preferences: SharedPreferences by lazy { this.getPreferences(MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalFarmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RefreshablePoetryPage(viewModel = viewModel, initialSection = preferences.getInt(preferredPoetrySectionKey, 0)) {
                        with(preferences.edit()) {
                            putInt(preferredPoetrySectionKey, it)
                            apply()
                        }
                    }
                }
            }
        }
        viewModel.fetchPoetry()
    }
}
