package com.artlvr.animalfarm.book

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AnimalFarmBookTest {
    private val animalFarm = AnimalFarmBook.makePoetry()

    @Test
    fun animalFarm_title() {
        assertEquals("21世纪农场", animalFarm.name)
    }

    @Test
    fun animalFarm_sections() {
        assertEquals(6, animalFarm.sections.count())
    }

    @Test
    fun animalFarm_poetryProviding() {
        assertTrue((AnimalFarmBook() is SyncPoetryProviding))
    }
}
