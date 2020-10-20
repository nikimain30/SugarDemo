package com.sugar.test.view.viewmodel

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.sugar.test.data.model.FetchProduct
import com.sugar.test.data.remote.api.Resource
import com.sugar.test.data.remote.api.apiCall
import com.sugar.test.data.remote.model.FetchDataDTO
import com.sugar.test.data.repo.HomeRepository
import dagger.hilt.android.qualifiers.ApplicationContext


class HomeViewModel@ViewModelInject constructor(
    private val homeRepository: HomeRepository,
    @ApplicationContext private val context: Context
): BaseViewModel() {

    val TAG = HomeRepository::class.java.simpleName
    var homeLiveData = MutableLiveData<Resource<List<FetchProduct>>>()
    private var fetchData = ArrayList<FetchProduct>()

    fun fetchData() {
        launchOnViewModelScope {
            homeLiveData.value = Resource.loading()
            apiCall<List<FetchDataDTO>?>(context) {
                onEnqueue = { homeRepository.fetchData() }
                onSuccess = { data ->
                    data?.let {
                        for (list in it) {
                            var user = FetchProduct()
                            user.id = list.id
                            user.contentData = list.contentData
                            user.title = list.title

                            fetchData.add(user)

                        }
                        homeLiveData.value = Resource.success(fetchData)
                    }

                }
                onError = {
                    homeLiveData.value = Resource.error(it)
                }
            }

        }
    }

}