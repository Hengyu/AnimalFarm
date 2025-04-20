package com.artlvr.animalfarm

import com.artlvr.animalfarm.networking.ArtlvrService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideArtlvrService(): ArtlvrService = ArtlvrService.default
}
