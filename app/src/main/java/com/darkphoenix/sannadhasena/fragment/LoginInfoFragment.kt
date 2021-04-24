package com.darkphoenix.sannadhasena.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.darkphoenix.sannadhasena.R
import com.darkphoenix.sannadhasena.databinding.FragmentLoginInfoBinding

class LoginInfoFragment : Fragment() {
    private var _binding: FragmentLoginInfoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginInfoFragment_to_loginNumFragment)
        }
        binding.signupBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginInfoFragment_to_signUpFragment)
        }
        return view
    }

}