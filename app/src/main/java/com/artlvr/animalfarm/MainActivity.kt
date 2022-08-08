package com.artlvr.animalfarm

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
                    RefreshablePoetryPage(
                        viewModel = viewModel,
                        initialSection = getPreferredSection(),
                        onSectionChanged = ::savePreferredSection,
                        onLongPress = ::showShareSheet
                    )
                }
            }
        }
        viewModel.fetchPoetry()
    }

    private fun getPreferredSection(): Int = preferences.getInt(preferredPoetrySectionKey, 0)

    private fun savePreferredSection(index: Int) {
        with(preferences.edit()) {
            putInt(preferredPoetrySectionKey, index)
            apply()
        }
    }

    private fun showShareSheet(offset: Offset) {
        val section = viewModel.poetry.sections[getPreferredSection()]
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, section.content)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
