package com.example.firestorepagination.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firestorepagination.databinding.ListItemBinding
import com.example.firestorepagination.model.UserMessages

class DataAdapter :
    PagingDataAdapter<UserMessages, DataAdapter.ViewHolder>(DiffCallback) {

    // viewHolder
    class ViewHolder(private var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(users: UserMessages) {
            binding.user = users
            binding.executePendingBindings()
        }

        // inflate layout from viewHolder
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(
                    layoutInflater, parent, false,
                )
                return ViewHolder(binding)
            }
        }
    }

    // layout inflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    // view Binder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}

// callback to compare content and item
object DiffCallback : DiffUtil.ItemCallback<UserMessages>() {
    override fun areItemsTheSame(oldItem: UserMessages, newItem: UserMessages): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: UserMessages, newItem: UserMessages): Boolean {
        return oldItem.timestamp == newItem.timestamp
    }

}