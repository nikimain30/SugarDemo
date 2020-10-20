package com.sugar.test.data.remote.api

import com.sugar.test.data.remote.model.FetchDataDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SugarApiService {

    @GET(Urls.fetchData)
    suspend fun fetchData(): Response<List<FetchDataDTO>?>
}