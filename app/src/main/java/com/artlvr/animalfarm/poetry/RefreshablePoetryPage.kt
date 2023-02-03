package com.artlvr.animalfarm.poetry

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RefreshablePoetryPage(
    viewModel: PoetryViewModel,
    initialSection: Int = 0,
    onSectionChanged: (Int) -> Unit = {},
    onLongPress: (Offset) -> Unit = {}
) {
    val isRefreshing by viewModel.isLoading.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { viewModel.loadPoetry() }
    )

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        PoetryPage(
            poetry = viewModel.poetry,
            initialSection = initialSection,
            onSectionChanged = onSectionChanged,
            onLongPress = onLongPress
        )

        PullRefreshIndicator(isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}
