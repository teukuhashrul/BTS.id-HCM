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
    fun getKaryawanById(
//      @Header("nip") nip:Int
        @Path("nip") nip: Int): Call<Karyawan>

    @PUT("editprofile/{nip}")
    fun editKaryawan(@Path("nip") nip: Int , @Body karyawan: Karyawan): Call<Karyawan>


    @POST("/login")
    @FormUrlEncoded
    fun loginKaryawan(@Field("email")email: String, @Field("password") password:String): Call<Karyawan>



}