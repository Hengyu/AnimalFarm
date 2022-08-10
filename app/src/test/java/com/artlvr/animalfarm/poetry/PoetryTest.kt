package com.artlvr.animalfarm.poetry

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.GregorianCalendar

class PoetryTest {

    private val poetry0 = Poetry(
        name = "1",
        sections = listOf(
            Poetry.Section(
                title = "title",
                content = "content",
                date = GregorianCalendar(2022, 1, 1).time
            )
        )
    )

    private val poetry1 = Poetry(
        name = "1",
        sections = listOf(
            Poetry.Section(
                title = "title",
                content = "content",
                date = GregorianCalendar(2022, 1, 1).time
            )
        )
    )

    @Test
    fun poetry_equatable() {
        assertEquals(poetry0, poetry1)
    }
}
