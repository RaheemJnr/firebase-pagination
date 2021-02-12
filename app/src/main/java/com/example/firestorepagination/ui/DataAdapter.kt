package com.example.firestorepagination.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firestorepagination.databinding.ListItemBinding
import com.example.firestorepagination.model.UserMessages

class DataAdapter :
    PagingDataAdapter<UserMessages, DataAdapter.UserViewHolder>(Companion) {

    inner class UserViewHolder(
        private val dataBinding: ListItemBinding
    ) : RecyclerView.ViewHolder(dataBinding.root) {
        fun bindProduct(user: UserMessages) {
            dataBinding.user = user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = ListItemBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return UserViewHolder(dataBinding)
    }

    // view Binder
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val product = getItem(position) ?: return
        holder.bindProduct(product)
    }

    companion object : DiffUtil.ItemCallback<UserMessages>() {
        override fun areItemsTheSame(oldItem: UserMessages, newItem: UserMessages): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserMessages, newItem: UserMessages): Boolean {
            return oldItem == newItem
        }
    }
}

