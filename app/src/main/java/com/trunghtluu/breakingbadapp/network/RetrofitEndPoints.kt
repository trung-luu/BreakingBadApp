package com.trunghtluu.breakingbadapp.network

import com.trunghtluu.breakingbadapp.model.Character
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitEndPoints {

    @GET("/api/characters")
    abstract fun getCharacters(): Call<List<Character>>

}