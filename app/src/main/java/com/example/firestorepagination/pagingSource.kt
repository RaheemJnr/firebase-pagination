package com.example.firestorepagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.firestorepagination.model.UserMessages
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class FirestorePagingSource(private val db: FirebaseFirestore) :
    PagingSource<QuerySnapshot, UserMessages>() {

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, UserMessages> {
        return try {
            val currentPage = params.key ?: db.collection("message").limit(10).get().result

            val lastDocumentSnapshot = currentPage?.documents?.get(currentPage.size())
            val nextPage = db.collection("message").limit(10).startAfter(lastDocumentSnapshot)
                .get()
                .result


            LoadResult.Page(
                data = currentPage!!.toObjects(UserMessages::class.java),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<QuerySnapshot, UserMessages>): QuerySnapshot? {
        TODO("Not yet implemented")
    }
}