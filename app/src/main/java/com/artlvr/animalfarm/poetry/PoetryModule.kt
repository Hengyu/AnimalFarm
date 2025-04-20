package com.artlvr.animalfarm.poetry

import com.artlvr.animalfarm.book.AnimalFarmBook
import com.artlvr.animalfarm.book.SyncPoetryProviding
import com.artlvr.animalfarm.networking.ArtlvrService
import com.artlvr.animalfarm.networking.AsyncAnimalFarmBook
import com.artlvr.animalfarm.networking.AsyncPoetryProviding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PoetryModule {
    @ViewModelScoped
    @Provides
    fun provideRemotePoetry(service: ArtlvrService): AsyncPoetryProviding = AsyncAnimalFarmBook(service = service)

    @ViewModelScoped
    @Provides
    fun provideLocalPoetry(): SyncPoetryProviding = AnimalFarmBook()
}
