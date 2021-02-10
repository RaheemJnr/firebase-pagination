package com.example.firestorepagination.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.firestorepagination.R
import com.example.firestorepagination.databinding.FragmentDataBinding
import com.example.firestorepagination.model.UserMessages
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class DataFragment : Fragment() {

    //binding and VM
    private lateinit var binding: FragmentDataBinding
    private lateinit var viewModel: DataViewModel

    private val adapter = DataAdapter()

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_data, container, false)

        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)


        //init fireStore
        val db = Firebase.firestore

        val message = UserMessages(
            "Hi, What's up", System.currentTimeMillis(),
            "https://picsum.photos/200",
            "User", 2L, 7L, "Jnr", "message"
        )

        //insert data
        for (i in 0..5) {
            db.collection("message").document().set(message)
        }

        //send inserted data to fire store

        binding.dataList.adapter = adapter

        lifecycleScope.launch {
            viewModel.incomingData.collect {
                Log.i("TAG", "onCreateView: $it")
                adapter.submitData(it)

            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
                binding.progressBarLoadMore.isVisible = loadStates.append is LoadState.Loading
            }
        }


        return binding.root
    }


}
