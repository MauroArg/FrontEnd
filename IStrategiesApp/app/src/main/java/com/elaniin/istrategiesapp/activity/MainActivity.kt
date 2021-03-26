package com.elaniin.istrategiesapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.elaniin.istrategiesapp.adapter.AccountAdapter
import com.elaniin.istrategiesapp.api.ResponseStatus
import com.elaniin.istrategiesapp.database.AppDatabase
import com.elaniin.istrategiesapp.database.getDatabase
import com.elaniin.istrategiesapp.databinding.ActivityMainBinding
import com.elaniin.istrategiesapp.model.user.Session
import com.elaniin.istrategiesapp.repository.LoginRepository
import com.elaniin.istrategiesapp.viewmodel.AccountViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AccountViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        val mLinearLayoutManager = LinearLayoutManager(this)

        binding.rcvAccount.layoutManager = mLinearLayoutManager

        val bundle = intent.extras!!
        val id = bundle.getLong("id")

        Log.e("USUARIO", id.toString())

        viewModel.GetAccount(id)

        val adapter = AccountAdapter(this)

        binding.rcvAccount.adapter = adapter

        binding.rcvAccount.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))


        adapter.setOnItemClickListener {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }

        viewModel.acList.observe(this, Observer {
            adapter.submitList(it)
            if (it.size == 0) {
                binding.rcvAccount.visibility = View.GONE
                binding.eqEmptyView.visibility = View.VISIBLE
            } else {
                binding.rcvAccount.visibility = View.VISIBLE
                binding.eqEmptyView.visibility = View.GONE
            }
        })

        viewModel.statusGet.observe(this, Observer {
            if (it == ResponseStatus.LOADING) {
                binding.loadingWheel.visibility = View.VISIBLE
            } else {
                binding.loadingWheel.visibility = View.GONE
            }

            if (it == ResponseStatus.NO_INTERNET) {
                Toast.makeText(this, "No posee conexion a  internet",
                    Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.userLiveData?.observe(this, Observer {
                userData ->
            if(userData == null){
                startLoginActivity()
            }
        })

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun startLoginActivity(){

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}