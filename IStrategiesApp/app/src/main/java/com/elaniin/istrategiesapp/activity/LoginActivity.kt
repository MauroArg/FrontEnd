package com.elaniin.istrategiesapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.elaniin.istrategiesapp.R
import com.elaniin.istrategiesapp.api.ResponseStatus
import com.elaniin.istrategiesapp.databinding.ActivityLoginBinding
import com.elaniin.istrategiesapp.fragment.LoginFragment
import com.elaniin.istrategiesapp.fragment.LoginFragmentDirections
import com.elaniin.istrategiesapp.fragment.SignupFragment
import com.elaniin.istrategiesapp.fragment.SignupFragmentDirections
import com.elaniin.istrategiesapp.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(), LoginFragment.BtnRegisterClicked, LoginFragment.BtnLoginClicked, SignupFragment.BtnSignupClicked {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.statusLogin.observe(this, Observer {
            when (it) {
                ResponseStatus.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
                ResponseStatus.DONE -> {
                    binding.progress.visibility = View.GONE
                    startMainActivity()
                }
                ResponseStatus.REJECTED -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
                ResponseStatus.NO_INTERNET -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, "No posee internet", Toast.LENGTH_SHORT).show()
                }
                ResponseStatus.ERROR -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, "A ocurrido un error", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.statusSignup.observe(this, Observer {
            when (it) {
                ResponseStatus.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
                ResponseStatus.DONE -> {
                    binding.progress.visibility = View.GONE
                    startLoginFragment()
                    Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show()
                }
                ResponseStatus.REJECTED -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, "Email ya existe", Toast.LENGTH_SHORT).show()
                }
                ResponseStatus.NO_INTERNET -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, "No posee internet", Toast.LENGTH_SHORT).show()
                }
                ResponseStatus.ERROR -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, "A ocurrido un error", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun startRegisterFragment(){
        findNavController(R.id.fragmentContainer)
            .navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment())
    }

    private fun startLoginFragment(){
        findNavController(R.id.fragmentContainer)
            .navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
    }

    private fun startMainActivity(){

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBtnLoginClicked(email: String, pass: String) {
        viewModel.login(email, pass)
    }

    override fun onBtnRegisterClicked() {
        startRegisterFragment()
    }

    override fun onBtnSignupClicked(email: String, name: String, pass: String) {
        viewModel.signup(email, name, pass)
    }


}