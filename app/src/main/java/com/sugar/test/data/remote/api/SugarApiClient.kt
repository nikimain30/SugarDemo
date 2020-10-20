package com.sugar.test.data.remote.api

import javax.inject.Inject

class SugarApiClient @Inject constructor(private val sugarApiService: SugarApiService) {

    suspend fun fetchData() = sugarApiService.fetchData()

}
