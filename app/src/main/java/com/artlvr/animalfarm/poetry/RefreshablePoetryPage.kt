package com.artlvr.animalfarm.poetry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RefreshablePoetryPage(
    viewModel: PoetryViewModel,
    initialSection: Int = 0,
    onSectionChanged: (Int) -> Unit = {},
    onLongPress: (Offset) -> Unit = {}
) {
    val isRefreshing by viewModel.isLoading.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = { viewModel.loadPoetry() }
    ) {
        PoetryPage(
            poetry = viewModel.poetry,
            initialSection = initialSection,
            onSectionChanged = onSectionChanged,
            onLongPress = onLongPress
        )
    }
}
