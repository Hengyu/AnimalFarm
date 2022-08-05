package com.artlvr.animalfarm.poetry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RefreshablePoetryPage(viewModel: PoetryViewModel, initialSection: Int = 0, onSectionChanged: (Int) -> Unit = {}) {
    val isRefreshing by viewModel.isFetching.collectAsState()

    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefreshing), onRefresh = { viewModel.fetchPoetry() }) {
        PoetryPage(
            poetry = viewModel.poetry,
            initialSection = initialSection,
            onSectionChanged = onSectionChanged
        )
    }
}
