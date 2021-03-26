package com.elaniin.istrategiesapp.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.elaniin.istrategiesapp.R
import com.elaniin.istrategiesapp.databinding.FragmentLoginBinding
import com.elaniin.istrategiesapp.utils.Crypto
import java.lang.ClassCastException

class LoginFragment : Fragment() {

    interface BtnLoginClicked{
        fun onBtnLoginClicked(email: String, pass: String)
    }

    interface BtnRegisterClicked{
        fun onBtnRegisterClicked()
    }

    private lateinit var btnLoginClicked: BtnLoginClicked
    private lateinit var btnRegisterClicked: BtnRegisterClicked

    private lateinit var view: FragmentLoginBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        btnLoginClicked = try {
            context as BtnLoginClicked
        } catch (e: Exception) {
            throw ClassCastException("$context must implement BtnLoginClicked")
        }

        btnRegisterClicked = try{
            context as BtnRegisterClicked
        } catch (e: Exception){
            throw ClassCastException("$context must implement BtnRegisterClicked")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = FragmentLoginBinding.inflate(inflater)


        view.btnLogin.setOnClickListener{
            if(!TextUtils.isEmpty(view.txtEmail.text) && !TextUtils.isEmpty(view.txtPass.text)){
                val crypto = Crypto()
                val pass = view.txtPass.text!!.toString()
                crypto.encriptar("0SPrEK0JntQ2qCm9cPEabw==", pass)?.let { it1 ->
                    btnLoginClicked.onBtnLoginClicked(view.txtEmail.text.toString(),
                        it1
                    )
                }
            }
            else{
                Toast.makeText(requireActivity(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        view.btnSignup.setOnClickListener{
            btnRegisterClicked.onBtnRegisterClicked()
        }

        return view.root
    }


}