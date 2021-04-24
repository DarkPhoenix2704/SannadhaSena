package com.darkphoenix.sannadhasena.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.exifinterface.media.ExifInterface
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.darkphoenix.sannadhasena.R
import com.darkphoenix.sannadhasena.databinding.FragmentSplashBinding
import com.darkphoenix.sannadhasena.utils.Preference
import com.darkphoenix.sannadhasena.utils.PrefsManager
import org.json.JSONTokener

class SplashFragment : Fragment() {

    companion object{
        private const val TAG = "SplashActivity :"
    }
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        if (PrefsManager.read(Preference.isLoggedIn, false)) {
            getLoginFromServer()
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_loginInfoFragment)
        }
        return view
    }
    private fun getLoginFromServer() {
        val requestQueue = Volley.newRequestQueue(requireContext())
        val regId: Long = PrefsManager.read(Preference.regId, 9999999999)
        val otp = PrefsManager.read(Preference.otp, null)

        val requestUri = StringBuilder()
        requestUri.append(getString(R.string.url))
        requestUri.append("/api/volunteerlogincheck/")
        requestUri.append(regId)
        requestUri.append("/")
        requestUri.append(otp)
        requestUri.append("/")
        Log.v(TAG, requestUri.toString())

        requestQueue.add(
            JsonObjectRequest(0, requestUri.toString(), null, {
                val str = "specialflag"
                try {
                    val nextValue = JSONTokener(it.get(str).toString()).nextValue()
                    if (nextValue is Int) {
                        Log.e("JsonString", it.toString())
                        when (ExifInterface.GPS_MEASUREMENT_2D) {
                            it.getString(str) -> {
                                Log.e("value", it.getString(str))
                                findNavController()
                                    .navigate(R.id.action_splashFragment_to_loginInfoFragment)
                            }
                            else -> {
                                findNavController()
                                    .navigate(R.id.action_splashFragment_to_homeFragment)
                            }
                        }
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }, {
                it.printStackTrace()
            })
        )
    }

}