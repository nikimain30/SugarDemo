package com.sugar.test.data.repo

import com.sugar.test.data.remote.api.SugarApiClient
import javax.inject.Inject

class HomeRepository @Inject constructor(private val sugarApiClient: SugarApiClient) {

    suspend fun fetchData() = sugarApiClient.fetchData()
}