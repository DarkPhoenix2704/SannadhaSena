package com.darkphoenix.sannadhasena.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.darkphoenix.sannadhasena.databinding.FragmentLoginOtpBinding
import com.darkphoenix.sannadhasena.utils.Preference
import com.darkphoenix.sannadhasena.utils.PrefsManager
import com.google.android.material.snackbar.Snackbar

class LoginOtpFragment : Fragment() {
    private var _binding: FragmentLoginOtpBinding? = null
    private val binding get() = _binding!!

    private var btn: ArrayList<TextView> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginOtpBinding.inflate(inflater, container, false)
        val view = binding.root

        initButtons()

        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
        for (i in 0..9) {
            btn[i].setOnClickListener {
                val text: String = binding.otpView.text.toString() + btn[i].text
                binding.otpView.setText(text)
            }
        }
        binding.otpNumPad.num10.setOnClickListener {
            val text: String = binding.otpView.text.toString().dropLast(1)
            binding.otpView.setText(text)
        }


        binding.otpView.setOtpCompletionListener {
            Snackbar.make(binding.root, "Verifying OTP", Snackbar.LENGTH_SHORT).show()
            verifyOtp()
        }
        return view
    }


    private fun initButtons() {
        btn.add(binding.otpNumPad.num0)
        btn.add(binding.otpNumPad.num1)
        btn.add(binding.otpNumPad.num2)
        btn.add(binding.otpNumPad.num3)
        btn.add(binding.otpNumPad.num4)
        btn.add(binding.otpNumPad.num5)
        btn.add(binding.otpNumPad.num6)
        btn.add(binding.otpNumPad.num7)
        btn.add(binding.otpNumPad.num8)
        btn.add(binding.otpNumPad.num9)
    }

    private fun verifyOtp() {
        val enteredOtp: String = binding.otpView.text.toString()
        val bundle = arguments ?: return
        val otp: Long = bundle["loginID"] as Long
        val phnNum: Long = bundle["otp"] as Long
        if (otp.toString() == enteredOtp) {
            PrefsManager.write(Preference.isLoggedIn, true)
            Snackbar.make(binding.root, "OTP Verified", Snackbar.LENGTH_SHORT).show()
            PrefsManager.write(Preference.regId, phnNum)
            PrefsManager.write(Preference.otp, otp)
        } else {
            Snackbar.make(binding.root, "Incorrect OTP", Snackbar.LENGTH_SHORT).show()
        }

    }


}
