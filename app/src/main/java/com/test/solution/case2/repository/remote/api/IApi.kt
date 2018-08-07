package com.test.solution.case2.repository.remote.api

import com.test.solution.case2.repository.apimodels.Location
import com.test.solution.case2.utils.Config
import retrofit2.Call
import retrofit2.http.GET

/**
 * Interface containing all the web services.
 *
 * created by fahad on 07/08/2018
 */
interface IApi {

    @GET(Config.API_ENDPOINT_SAMPLE)
    fun getAllLocation() : Call<List<Location>>
}