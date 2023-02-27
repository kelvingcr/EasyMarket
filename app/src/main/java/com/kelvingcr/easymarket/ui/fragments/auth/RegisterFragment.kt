package com.kelvingcr.easymarket.ui.fragments.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kelvingcr.easymarket.MainActivity
import com.kelvingcr.easymarket.R
import com.kelvingcr.easymarket.databinding.FragmentRegisterBinding
import com.kelvingcr.easymarket.ui.fragments.auth.viewmodel.AuthFragmentViewModel
import com.kelvingcr.easymarket.utils.Constants.Companion.ON_SUCESS_REGISTER
import com.kelvingcr.easymarket.utils.Utils
import com.kelvingcr.easymarket.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val binding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<AuthFragmentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.signUpLiveData.observe(viewLifecycleOwner, Observer {
            if(it == ON_SUCESS_REGISTER) {
                requireActivity().finish()
                requireActivity().startActivity(Intent(requireActivity(), MainActivity::class.java))
                showToast(it)
            } else {
                showToast(getString(Utils.validError(it)))
            }
        })

        configClicks()
    }

    private fun configClicks() {
        binding.btnClick.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if(email.isNotBlank() && email.isNotEmpty()) {
                if(password.isNotBlank() && password.isNotEmpty()) {

                   viewModel.signUp(email, password)

                } else {
                    binding.edtPassword.error = getString(R.string.onFieldError)
                    binding.edtPassword.requestFocus()
                }
            } else {
                binding.edtEmail.error = getString(R.string.onFieldError)
                binding.edtEmail.requestFocus()
            }


        }
    }
    



}