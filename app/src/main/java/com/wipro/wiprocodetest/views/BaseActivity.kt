package com.wipro.wiprocodetest.views

import android.content.IntentFilter
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar.make
import com.wipro.wiprocodetest.R
import com.wipro.wiprocodetest.utilities.ConnectivityReceiver


open class BaseActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    private var mSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(ConnectivityReceiver(), filter)
    }

    private fun showMessage(isConnected: Boolean) {
        if (!isConnected) {

            val messageToUser = "You are offline now."
            //"rootLayout" as the root layout of every activity.
            mSnackBar = make(findViewById(R.id.rootLayout), messageToUser, LENGTH_INDEFINITE)
            mSnackBar?.show()

        } else {
            mSnackBar?.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(ConnectivityReceiver())
    }
}