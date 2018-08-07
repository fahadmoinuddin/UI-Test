package com.test.solution.case2.repository

import com.test.solution.case2.repository.apimodels.Location
import com.test.solution.case2.repository.remote.RemoteRepository

/**
 * Repository acts as a medium between view model and data.
 * Communicates with Remote repository to get the data.
 *
 * created by fahad on 07/08/2018
 */
class Repository private constructor(val remoteRepository: RemoteRepository?) {

    //Singleton instance
    companion object {
        private val repository = Repository(RemoteRepository.getInstance())

        fun getInstance() = repository
    }

    //This function is called to get all locations from the URL. Based on the response, either succeehandler or failurehandler is called.
    fun getAllLocations(successHandler: (List<Location>?) -> Unit, failureHandler: (Throwable?) -> Unit){
        remoteRepository?.getAllLocations(successHandler, failureHandler)
    }

}