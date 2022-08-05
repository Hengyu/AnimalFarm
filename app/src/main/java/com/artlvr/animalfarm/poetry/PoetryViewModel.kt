package com.artlvr.animalfarm.poetry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artlvr.animalfarm.book.SyncPoetryProviding
import com.artlvr.animalfarm.networking.AsyncPoetryProviding
import kotlinx.coroutines.launch

class PoetryViewModel(private val local: SyncPoetryProviding, private val remote: AsyncPoetryProviding) : ViewModel() {
    var poetry: Poetry by mutableStateOf(local.getPoetry())

    fun fetchPoetry() {
        viewModelScope.launch {
            val result = remote.getPoetry()
            if (result.isSuccess) {
                poetry = result.getOrThrow()
            }
        }
    }
}
