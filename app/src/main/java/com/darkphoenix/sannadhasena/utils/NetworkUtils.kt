package com.darkphoenix.sannadhasena.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.provider.Settings
import android.view.View
import com.google.android.material.snackbar.Snackbar

object NetworkUtils {
    private lateinit var mContext: Context
    private lateinit var mView: View
    fun init(context: Context, view: View) {
        mContext = context
        mView = view
    }

    var isConnected: Boolean = false
    fun registerNetworkCallback() {
        val networkSnackBar =
            Snackbar.make(mView, "No Internet Connection", Snackbar.LENGTH_INDEFINITE)
                .setAction("Settings") {
                    mContext.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
                }
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        cm.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isConnected = true
                networkSnackBar.dismiss()
            }

            override fun onLost(network: Network) {
                isConnected = false
                networkSnackBar.show()
            }
        })
    }

}