package dev.vladmakarenko.financialPlanning.core.repository.remote

import dev.vladmakarenko.financialPlanning.core.model.NetworkRequest
import dev.vladmakarenko.financialPlanning.core.model.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkService {
    @POST("api/url/getUrl")
    suspend fun requestResponse(@Body request: NetworkRequest): NetworkResponse
}