package com.example.submission5.network

import com.example.submission5.model.action.ResponseAction
import com.example.submission5.model.getdata.ResponseData
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // get data
    @GET("getdata.php")
    fun getData() : Flowable<ResponseData>

    //insert data
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData(@Field("nama") nama : String,
                   @Field("nohp") nohp : String,
                   @Field("alamat") alamat : String

    ) : Single<ResponseAction>

    // update data
    @FormUrlEncoded
    @POST("update.php")
    fun updateData(@Field("id") id : String,
                   @Field("nama") nama : String,
                   @Field("nohp") nohp : String,
                   @Field("alamat") alamat : String

    ) : Single<ResponseAction>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(@Field("id") id : String) : Single<ResponseAction>
}