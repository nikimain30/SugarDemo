package com.sugar.test.view.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.sugar.test.R
import com.sugar.test.data.model.Product
import com.sugar.test.databinding.ActivityHomeBinding
import com.sugar.test.databinding.ActivityWebviewBinding
import com.sugar.test.utility.AppConstant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_webview.*

@AndroidEntryPoint
class WebViewActivity : BaseActivity<ActivityWebviewBinding>() {

    companion object {
        fun getCallingIntent(context: Context, data: String): Intent {
            val bundle = Bundle()
            bundle.putString(AppConstant.BUNDLE_PRODUCT_DATA, data)
            return Intent(context, WebViewActivity::class.java).putExtras(bundle)
        }
    }

    private fun getBundleData() {
        if (intent.hasExtra(AppConstant.BUNDLE_PRODUCT_DATA)) {
            product = (intent.extras?.getString(AppConstant.BUNDLE_PRODUCT_DATA))!!
        }
    }

    lateinit var product: String

    override val layoutId: Int
        get() = R.layout.activity_webview

    override fun init() {
       getBundleData()
        setListener()
    }

    private fun setListener() {
        webView.webViewClient = MyWebViewClient(this)

        if (product.equals("https://d32baadbbpueqt.cloudfront.net/dashboard/1602700732Festive-Glam-Kit-Mobile-Homepage-Banner.jpg")) {
            webView.loadUrl("https://in.sugarcosmetics.com/products/matte-as-hell-crayon-lipstick")
        } else if (product.equals("https://d32baadbbpueqt.cloudfront.net/dashboard/1602442711MAH--Homepage-banner_600x500_App.gif")){
            webView.loadUrl("https://in.sugarcosmetics.com/products/matte-as-hell-crayon-lipstick")
        } else if (product.equals("https://d32baadbbpueqt.cloudfront.net/dashboard/1602233360Lockdown-wedding-makeup-look---Mobile-banner.jpg")) {
            webView.loadUrl("https://blog.sugarcosmetics.com/quick-makeup-look-for-post-lockdown-weddings/")
        }
    }

    class MyWebViewClient internal constructor(private val activity: Activity) : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url: String = request?.url.toString();
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
        }
    }
}