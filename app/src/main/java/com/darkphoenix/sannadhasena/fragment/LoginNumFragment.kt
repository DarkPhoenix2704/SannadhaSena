package com.darkphoenix.sannadhasena.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.darkphoenix.sannadhasena.R
import com.darkphoenix.sannadhasena.databinding.FragmentLoginNumBinding
import com.darkphoenix.sannadhasena.utils.NetworkUtils
import com.google.android.material.snackbar.Snackbar

class LoginNumFragment : Fragment() {
    companion object {
        private const val TAG = "Num Fragment"
    }

    private var _binding: FragmentLoginNumBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginNumBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.buttonNext.setOnClickListener {
            attemptLogin()
        }
        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
        return view
    }

    private fun attemptLogin() {
        val number = binding.includeLoginCard.editTextPhone.text
        val status: Int = if (number.toString().isEmpty() || number.length != 10) {
            1
        } else {
            0
        }
        if (status == 1) {
            binding.includeLoginCard.editTextPhone.requestFocus()
        } else {
            if (NetworkUtils.isConnected) {
                loginRequest()
            }
        }
    }

    private fun loginRequest() {
        val loginID: Long = binding.includeLoginCard.editTextPhone.text.toString().toLong()
        val url: StringBuilder = StringBuilder()
        url.append((context?.getString(R.string.url)))
        url.append("/api/volunteerloginotp/")
        url.append(loginID)
        Log.v(TAG, url.toString())
        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(JsonObjectRequest(0, url.toString(), null, {
            val str = "otp"
            val otp = it.getString(str).toString()
            try {
                if (otp == "0") {
                    Snackbar.make(binding.root, "Invalid Credentials", Snackbar.LENGTH_SHORT)
                        .show()
                    binding.includeLoginCard.editTextPhone.requestFocus()
                } else {
                    val args = Bundle()
                    args.putLong("loginID", loginID)
                    args.putLong("otp", otp.toLong())
                    findNavController().navigate(
                        R.id.action_loginNumFragment_to_loginOtpFragment,
                        args
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, {
            Log.v(TAG, it.toString())
        }))

    }
}