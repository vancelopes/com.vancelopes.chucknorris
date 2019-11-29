package com.vancelopes.chucknorris.api

import com.vancelopes.chucknorris.model.Joke
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckAPI {
    @GET("categories")
    fun getCategories(): Observable<List<String>>

    @GET("random")
    fun getRandomJoke(@Query("category") category: String): Observable<Joke>
}