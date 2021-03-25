package com.elaniin.istrategiesapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.elaniin.istrategiesapp.R
import com.elaniin.istrategiesapp.databinding.ActivityLoginBinding
import com.elaniin.istrategiesapp.fragment.LoginFragment
import com.elaniin.istrategiesapp.fragment.LoginFragmentDirections
import com.elaniin.istrategiesapp.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(), LoginFragment.BtnRegisterClicked, LoginFragment.BtnLoginClicked {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    }

    private fun startRegisterFragment(){
        findNavController(R.id.fragmentContainer)
            .navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment())
    }

    private fun startMainActivity(){

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBtnLoginClicked(email: String, pass: String) {
        viewModel.login(email,pass)
    }

    override fun onBtnRegisterClicked() {
        startRegisterFragment()
    }


}