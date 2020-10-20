package com.sugar.test.view.navigator

import android.app.Activity
import com.sugar.test.data.model.Product
import com.sugar.test.view.activity.ProductDetailActivity
import com.sugar.test.view.activity.WebViewActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityNavigator @Inject constructor(){

    fun navigateToProductDetail (activity: Activity, data : Product) {
        activity.startActivity(ProductDetailActivity.getCallingIntent(activity, data))
    }

    fun navigateToRedirectUrl(activity: Activity, data : String) {
        activity.startActivity(WebViewActivity.getCallingIntent(activity, data))
    }
}