package com.test.solution.case2.repository.remote

import com.test.solution.case2.repository.apimodels.Location
import com.test.solution.case2.repository.remote.api.Apis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Remote Repository acts as the medium to communicate with web services and returns the
 * results to main Repository.
 *
 * created by fahad on 07/08/2018
 */
class RemoteRepository private constructor() {

    //Singleton instance
    companion object {
        private val remoteRepository = RemoteRepository()

        fun getInstance() = remoteRepository
    }

    //Get all location from the web service. Calls successhandler if the response is received successfully, else error is thrown.
    fun getAllLocations(successHandler: (List<Location>?) -> Unit, failureHandler: (Throwable?) -> Unit){

        Apis.getApi().getAllLocation().enqueue(object : Callback<List<Location>>{
            override fun onResponse(call: Call<List<Location>>?, response: Response<List<Location>>?) {
                response?.body()?.let {
                    //call succeehandler with the list of locations
                    successHandler(it)
                }
            }

            override fun onFailure(call: Call<List<Location>>?, t: Throwable?) {
                //call failurehandler with the error.
                failureHandler(t)
            }
        })
    }
}