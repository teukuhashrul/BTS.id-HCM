package com.example.hcmbtsid.data.model

import retrofit2.Call
import retrofit2.http.*

/**
 * Interface to handle request related to user
 *
 */
interface UserServices {

    @GET("comments")
    fun getUser(): Call<List<User_Retrofit>>


    @GET("karyawan/{nip}")
    fun getKaryawanById(@Path("nip") nip: Int): Call<Karyawan>


    @POST("/login")
    @FormUrlEncoded
    fun loginKaryawan(@Field("email")email: String, @Field("password") password:String): Call<Karyawan>
}