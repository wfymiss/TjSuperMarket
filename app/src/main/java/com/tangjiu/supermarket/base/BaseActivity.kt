package com.tangjiu.supermarket.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {
    var process: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()

    }

    abstract fun getLayoutId(): Int
    abstract fun initView()

    fun showDialog(msg: String) {
        process = ProgressDialog(this)
        process?.setMessage(msg)
        process?.show()
    }

    fun dissDialogprocess() {
        process?.dismiss()
        process = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}