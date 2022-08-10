package com.artlvr.animalfarm.poetry

import com.artlvr.animalfarm.book.SyncPoetryProviding
import com.artlvr.animalfarm.networking.AsyncPoetryProviding
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.GregorianCalendar
import kotlin.time.Duration.Companion.milliseconds

class PoetryViewModelTest {
    private val local = mockk<SyncPoetryProviding>()
    private val remote = mockk<AsyncPoetryProviding>()
    private lateinit var viewModel: PoetryViewModel
    private val dispatcher = UnconfinedTestDispatcher()
    private val remoteWorkDelay = 20.milliseconds

    private val localPoetry = Poetry(
        name = "1",
        sections = listOf(
            Poetry.Section(
                title = "title",
                content = "content",
                date = GregorianCalendar(2022, 1, 1).time
            )
        )
    )
    private val remotePoetry = Poetry(
        name = "2",
        sections = listOf(
            Poetry.Section(
                title = "title",
                content = "content",
                date = GregorianCalendar(2022, 5, 1).time
            )
        )
    )

    @Before
    fun setup() {
        every { local.getPoetry() } returns localPoetry
        coEvery { remote.getPoetry() } coAnswers {
            delay(remoteWorkDelay)
            Result.success(remotePoetry)
        }
        viewModel = PoetryViewModel(local = local, remote = remote)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun poetryValue_local() {
        assertEquals(viewModel.poetry, localPoetry)
    }

    @Test
    fun poetryValue_remote() = runTest {
        viewModel.loadPoetry()
        delay(remoteWorkDelay)
        assertEquals(viewModel.poetry, remotePoetry)
    }

    @Test
    fun isLoading_idle() {
        val viewModel = PoetryViewModel(local = local, remote = remote)
        assertFalse(viewModel.isLoading.value)
    }

    @Test
    fun isLoading_running() = runTest {
        val viewModel = PoetryViewModel(local = local, remote = remote)
        viewModel.loadPoetry()
        assertTrue(viewModel.isLoading.value)
        delay(remoteWorkDelay)
        assertFalse(viewModel.isLoading.value)
    }
}
