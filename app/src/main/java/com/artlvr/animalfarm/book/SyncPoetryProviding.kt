package com.artlvr.animalfarm.book

import com.artlvr.animalfarm.poetry.Poetry

interface SyncPoetryProviding {

    fun getPoetry(): Poetry
}
