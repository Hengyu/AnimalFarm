package com.artlvr.animalfarm

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import com.artlvr.animalfarm.logging.logger
import com.artlvr.animalfarm.poetry.PoetryViewModel
import com.artlvr.animalfarm.poetry.RefreshablePoetryPage
import com.artlvr.animalfarm.ui.theme.AnimalFarmTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: PoetryViewModel by viewModels()

    private val preferredPoetrySectionKey: String = "preferredPoetrySection"
    private val preferences: SharedPreferences by lazy { this.getPreferences(MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalFarmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    RefreshablePoetryPage(
                        viewModel = viewModel,
                        initialSection = getPreferredSection(),
                        onSectionChanged = ::savePreferredSection,
                        onLongPress = ::showShareSheet,
                    )
                }
            }
        }
        viewModel.loadPoetry()
    }

    private fun getPreferredSection(): Int = preferences.getInt(preferredPoetrySectionKey, 0)

    private fun savePreferredSection(index: Int) {
        with(preferences.edit()) {
            putInt(preferredPoetrySectionKey, index)
            apply()
        }
        logger.d("Poetry section did update to $index")
    }

    private fun showShareSheet(offset: Offset) {
        val section = viewModel.poetry.sections[getPreferredSection()]
        val sendIntent: Intent =
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, section.content)
                type = "text/plain"
            }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

        logger.d("Start share activity with poetry by section $section")
    }
}
