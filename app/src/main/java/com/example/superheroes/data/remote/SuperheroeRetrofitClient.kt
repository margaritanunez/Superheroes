package com.example.superheroes.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperheroeRetrofitClient {
    companion object{
        private const val BASE_URL = "https://y-mariocanedo.vercel.app/"

        fun getRetrofitSuperheroe() : SuperheroeApi{
            val mRetrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return mRetrofit.create(SuperheroeApi::class.java)
        }
    }
}