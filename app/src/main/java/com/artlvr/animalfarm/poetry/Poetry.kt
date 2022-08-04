package com.artlvr.animalfarm.poetry

import java.util.Date

data class Poetry(val name: String, val sections: List<Section>) {
    data class Section(val title: String, val content: String, val date: Date)
}
