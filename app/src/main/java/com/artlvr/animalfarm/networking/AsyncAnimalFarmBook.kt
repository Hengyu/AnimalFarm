package com.artlvr.animalfarm.networking

import com.artlvr.animalfarm.poetry.Poetry
import javax.inject.Inject

class AsyncAnimalFarmBook @Inject constructor(private val service: ArtlvrService) : AsyncPoetryProviding {

    class InvalidName : Throwable(message = "Invalid poetry name")

    override suspend fun getPoetry(): Result<Poetry> {
        try {
            val response = service.getPoetry(name = "animalFarm")
            response.body()?.poetry?.let {
                return Result.success(it)
            }
            return Result.failure(InvalidName())
        } catch (exception: Exception) {
            return Result.failure(exception)
        }
    }
}
