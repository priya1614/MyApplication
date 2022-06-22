package com.example.myapplication.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.data.api.ApiHelperImpl
import com.example.myapplication.data.api.RetrofitBuilder
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.utils.Status
import com.example.myapplication.utils.ViewModelFactory
import com.example.myapplication.viewmodel.LoginViewModel

class LoginFragment : BaseFragment() {
    private lateinit var viewModel: LoginViewModel
    override fun layoutId(): Int = R.layout.fragment_login
    private var signinButton: Button? = null
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModel()
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signinButton = view.findViewById(R.id.btn_login)
        signinButton?.setOnClickListener {
            if (TextUtils.isEmpty(binding.inputEmail.text.toString())) {
                binding.inputEmail.error = getString(R.string.username_epty_error)
            } else if (TextUtils.isEmpty(binding.inputPassword.text.toString())) {
                binding.inputPassword.error = getString(R.string.password_empty_error_msg)
            } else {
                setupObserver()
            }
        }
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            when (it?.status) {
                Status.SUCCESS -> {
                    val dashboardFragment = DashboardFragment()
                    val transaction = mActivity?.supportFragmentManager?.beginTransaction()
                    transaction?.add(R.id.parent_container, dashboardFragment)
                    // transaction?.disallowAddToBackStack()
                    transaction?.remove(LoginFragment())
                    transaction?.commit()
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    Toast.makeText(context, getString(R.string.please_try_again_later), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(LoginViewModel::class.java)
    }

}