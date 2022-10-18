package com.artlvr.animalfarm.poetry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artlvr.animalfarm.book.SyncPoetryProviding
import com.artlvr.animalfarm.logging.logger
import com.artlvr.animalfarm.networking.AsyncPoetryProviding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoetryViewModel @Inject constructor(
    private val local: SyncPoetryProviding,
    private val remote: AsyncPoetryProviding
) : ViewModel() {
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var poetry: Poetry by mutableStateOf(local.getPoetry())
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadPoetry() {
        logger.d("Start loading poetry")
        _isLoading.value = true
        viewModelScope.launch {
            val result = remote.getPoetry()
            if (result.isSuccess) {
                poetry = result.getOrThrow()
            }
            _isLoading.value = false
        }
    }
}
