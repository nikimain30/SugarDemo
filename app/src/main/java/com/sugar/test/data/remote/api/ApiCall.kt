package com.sugar.test.data.remote.api

import android.content.Context
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class ApiCallFunction<T> {

    lateinit var onSuccess: (data : T?) -> Unit
    lateinit var onEnqueue: suspend () -> Response<T>
    lateinit var onError: (error : Error) -> Unit
}

suspend fun <X> apiCall(context: Context? = null, call: ApiCallFunction<X>.() -> Unit) {
    val apiCallFunction = ApiCallFunction<X>()
    call(apiCallFunction)
    if (NetworkUtils.isNetworkConnected(context!!)) {
        try {
            val response = apiCallFunction.onEnqueue()
            parseResponse(response, apiCallFunction, context)
        } catch (ex : Exception) {
            Timber.d(ex)
            when(ex) {
                is IOException -> {
                    apiCallFunction.onError(Error("Network Error")) }
                is HttpException -> {
                    apiCallFunction.onError(Error(getErrorMessage(ex))) }
                else -> {
                    apiCallFunction.onError(Error("Something Went Wrong")) }
            }
        }
    } else {
        apiCallFunction.onError(Error("No Internet"))
    }
}

fun <X> parseResponse(response: Response<X>, apiCallFunction: ApiCallFunction<X>, context: Context?) {
    if(response != null) {
        apiCallFunction.onSuccess(response.body())
    } else {
        apiCallFunction.onError(Error("Something Went Wrong, Null Response"))
    }
}

private fun getErrorMessage(throwable: HttpException) : String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        null
    }
}