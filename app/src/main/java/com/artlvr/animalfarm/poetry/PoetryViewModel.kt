package com.artlvr.animalfarm.poetry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artlvr.animalfarm.book.AnimalFarmBook
import com.artlvr.animalfarm.networking.AsyncPoetryProviding
import kotlinx.coroutines.launch

class PoetryViewModel(private val poetryProviding: AsyncPoetryProviding) : ViewModel() {
    var poetry: Poetry by mutableStateOf(AnimalFarmBook.makePoetry())

    fun fetchPoetry() {
        viewModelScope.launch {
            val result = poetryProviding.getPoetry()
            if (result.isSuccess) {
                poetry = result.getOrThrow()
            }
        }
    }
}
