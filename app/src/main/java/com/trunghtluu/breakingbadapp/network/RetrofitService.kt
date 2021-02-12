package com.trunghtluu.breakingbadapp.network

import com.trunghtluu.breakingbadapp.model.Character
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private val endpoint: RetrofitEndPoints

    init {
        endpoint = createEndPoint(getInstance())
    }

    private fun getInstance(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

        return Retrofit.Builder()
                .baseUrl("https://breakingbadapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }

    fun createEndPoint(retrofit: Retrofit): RetrofitEndPoints {
        return retrofit.create(RetrofitEndPoints::class.java)
    }

    fun getCharacters(): Call<List<Character>> {
        return endpoint.getCharacters()
    }
}