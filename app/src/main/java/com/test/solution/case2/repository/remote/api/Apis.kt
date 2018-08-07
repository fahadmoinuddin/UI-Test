package com.test.solution.case2.repository.remote.api

import com.google.gson.GsonBuilder
import com.test.solution.case2.utils.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Initialize retrofit.
 *
 * created by fahad on 07/08/2018
 */
object Apis {

    fun getApi() : IApi{
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(IApi::class.java)
    }
}