package com.sugar.test.view.activity

import android.app.ProgressDialog
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sugar.test.R
import com.sugar.test.view.navigator.ActivityNavigator
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding>  : AppCompatActivity() {

    protected val binding: T by lazy { DataBindingUtil.setContentView(this, layoutId) as T }
    abstract val layoutId: Int
    private var progressDialog: ProgressDialog? = null

    @Inject
    lateinit var activityNavigator : ActivityNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        init()
    }

    abstract fun init()

    /**
     *  Progressbar Dialog
     */
    private val progressBar: AlertDialog? by lazy {
        this.let {
            AlertDialog.Builder(it, R.style.ProgressDialog).setView(R.layout.layout_loading)
                .setCancelable(false).create()
        }
    }

    fun initLoader() {
        progressBar?.let { myProgressBar ->
            if (!myProgressBar.isShowing) {
                myProgressBar.show()
            } else {
                if (this.isFinishing) {
                    myProgressBar.show()
                }
            }
        }
    }

    fun finishLoader() {
        try {
            progressBar?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun initLoader(message: String, isCancelable: Boolean) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
        }

        progressDialog!!.setMessage(message)
        progressDialog!!.setCancelable(isCancelable)
        progressDialog!!.show()

    }

    fun finishDialog() {
        progressDialog!!.dismiss()
    }

    fun showStatusBar() {
        val window: Window = getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    }

    override fun onResume() {
        super.onResume()
    }

}