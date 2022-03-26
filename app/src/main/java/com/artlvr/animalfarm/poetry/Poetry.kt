package com.artlvr.animalfarm.poetry

import java.time.LocalDate

data class Poetry(val name: String, val sections: List<Section>) {
    data class Section(val title: String, val content: String, val date: LocalDate)
}
