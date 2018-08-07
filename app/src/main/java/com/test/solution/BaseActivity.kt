package com.test.solution

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast

/**
 * Abstract class to display progress dialog and display toasts.
 * All activities extends this class.
 *
 * created by fahad on 07/08/2018
 */
abstract class BaseActivity : AppCompatActivity() {

    internal var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            progressDialog = createProgressDialog()
        } catch (e: Exception) {

        }
    }

    protected fun closeProgress() {
        try {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        } catch (e: Exception) {

        }
    }

    protected fun showProgress() {
        try {
            if (progressDialog != null) {
                progressDialog!!.show()
            }
        } catch (e: Exception) {

        }
    }

    protected fun createProgressDialog(): ProgressDialog? {
        var progressDialog: ProgressDialog? = null
        try {
            progressDialog = ProgressDialog(this)
            progressDialog.isIndeterminate = false
            progressDialog.setMessage("Loading...")
            progressDialog.setCancelable(false)
        } catch (e: Exception) {

        }
        return progressDialog
    }

    override fun onDestroy() {
        try {
            super.onDestroy()
            closeProgress()
        } catch (e: Exception) {

        }
    }

    fun showToast(message: String?){
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show();
    }
}
