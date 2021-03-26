package com.elaniin.istrategiesapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elaniin.istrategiesapp.databinding.ItemAccountBinding
import com.elaniin.istrategiesapp.model.account.Account

class AccountAdapter(private val context: Context): ListAdapter<Account, AccountAdapter.ViewHolder>(DiffCallback){
    companion object DiffCallback: DiffUtil.ItemCallback<Account>(){
        override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
            return oldItem.name == newItem.name
        }
    }

    private lateinit var onItemClickListener: ((account: Account) -> Unit)

    fun setOnItemClickListener(onItemClickListener: (account: Account) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAccountBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }


    inner class ViewHolder(private val binding: ItemAccountBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(account: Account){
            binding.txtName.text = account.name
            binding.root.setOnClickListener{
                if(::onItemClickListener.isInitialized){
                    onItemClickListener(account)
                }
                else{
                    Log.e("Region adapter", "On item click listener not initialized")
                }
            }
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val account = getItem(position)
        holder.bind(account)
    }

}