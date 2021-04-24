package com.darkphoenix.sannadhasena.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.darkphoenix.sannadhasena.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {
    private var _binding :FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }
}