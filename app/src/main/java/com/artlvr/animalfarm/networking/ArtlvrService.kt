package com.artlvr.animalfarm.networking

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://artlvr.herokuapp.com"

val gson = GsonBuilder()
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
    .create()

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

interface ArtlvrService {
    @GET("poetry")
    suspend fun getPoetry(@Query(value = "name", encoded = true) name: String): Response<PoetryResponse>

    companion object {
        val default: ArtlvrService by lazy {
            retrofit.create(ArtlvrService::class.java)
        }
    }
}
