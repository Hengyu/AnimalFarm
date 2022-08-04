package com.artlvr.animalfarm.networking

import com.artlvr.animalfarm.poetry.Poetry

interface AsyncPoetryProviding {

    suspend fun getPoetry(): Result<Poetry>
}
