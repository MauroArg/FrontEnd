package com.elaniin.istrategiesapp.fragment

import android.R
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.elaniin.istrategiesapp.databinding.FragmentSignupBinding
import com.elaniin.istrategiesapp.utils.Crypto

class SignupFragment : Fragment() {

    interface BtnSignupClicked{
        fun onBtnSignupClicked(email: String, name: String, pass: String)
    }

    private lateinit var btnSignupClicked: BtnSignupClicked

    private lateinit var view: FragmentSignupBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        btnSignupClicked = try{
            context as BtnSignupClicked
        } catch (e: Exception){
            throw ClassCastException("$context must implement BtnSignupClicked")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = FragmentSignupBinding.inflate(inflater)

        view.btnSignup.setOnClickListener {

            if(!TextUtils.isEmpty(view.txtEmail.text) && !TextUtils.isEmpty(view.txtUser.text) && !TextUtils.isEmpty(
                    view.txtPass.text
                )){
                val crypto: Crypto = Crypto()
                val pass = view.txtPass.text!!.toString()
                crypto.encriptar("0SPrEK0JntQ2qCm9cPEabw==", pass)?.let { it1 ->
                    btnSignupClicked.onBtnSignupClicked(
                        view.txtEmail.text.toString(),
                        view.txtUser.text.toString(),
                        it1
                    )
                }
            }
            else{
                Toast.makeText(
                    requireActivity(),
                    "Por favor complete todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return view.root
    }
}