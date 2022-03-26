package com.artlvr.animalfarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.artlvr.animalfarm.book.AnimalFarmBook
import com.artlvr.animalfarm.poetry.PoetryPage
import com.artlvr.animalfarm.ui.theme.AnimalFarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalFarmTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PoetryPage(poetry = AnimalFarmBook.makePoetry())
                }
            }
        }
    }
}
