package com.orivas.dogs.data.service


import com.orivas.dogs.data.model.DogResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogsService {

    @GET("1151549092634943488")
    suspend fun getAllDogs(): Response<List<DogResponse>>

}