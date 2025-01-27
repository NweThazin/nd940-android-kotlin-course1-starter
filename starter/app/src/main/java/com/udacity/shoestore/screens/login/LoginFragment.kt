package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_login,
                container,
                false
        )
        binding.btnLogin.setOnClickListener {
            if (isValidate()) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            } else {
                Toast.makeText(requireContext(), R.string.error_required_fields, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }

        return binding.root
    }

    private fun isValidate(): Boolean {
        return !TextUtils.isEmpty(binding.etEmail.text.toString().trim()) && !TextUtils.isEmpty(binding.etPassword.text.toString().trim())
    }
}