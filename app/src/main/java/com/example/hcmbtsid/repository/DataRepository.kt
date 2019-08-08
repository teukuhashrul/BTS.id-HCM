package com.example.hcmbtsid.repository

import com.example.hcmbtsid.data.model.UserServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  Class for building retrofit connection
 */
object DataRepository {

    fun create(): UserServices {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://private-7678e1-aaww.apiary-mock.com")
            .build()
        return retrofit.create(UserServices::class.java)
    }
}