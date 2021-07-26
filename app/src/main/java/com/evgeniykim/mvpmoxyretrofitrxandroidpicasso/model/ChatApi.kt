package com.evgeniykim.mvpmoxyretrofitrxandroidpicasso.model


import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ChatApi {

    @GET("")
    fun loadJson() : Observable<JsonObject>

    companion object Factory{

        fun create(): ChatApi {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("")
                .build()

            return retrofit.create(ChatApi::class.java)
        }
    }
}