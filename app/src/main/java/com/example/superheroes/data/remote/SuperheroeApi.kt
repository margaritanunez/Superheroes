package com.example.superheroes.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroeApi {

    @GET("superheroes/")
    suspend fun getDataSuperheroe(): Response<List<Superheroe>>

    @GET("superheroes/{id}")
    suspend fun getDataDetailSuperheroe(@Path("id") id: Int): Response<DetailSuperheroe>
}