package com.example.firestorepagination.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.firestorepagination.FirestorePagingSource
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DataViewModel : ViewModel() {


    val incomingData = Pager(
        PagingConfig(1)
    ) {
        FirestorePagingSource(Firebase.firestore)
    }.flow.cachedIn(viewModelScope)

}