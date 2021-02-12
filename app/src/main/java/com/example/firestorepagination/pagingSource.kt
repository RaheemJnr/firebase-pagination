package com.example.firestorepagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.firestorepagination.model.UserMessages
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class FirestorePagingSource(
    private val db: FirebaseFirestore
) : PagingSource<QuerySnapshot, UserMessages>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, UserMessages>): QuerySnapshot? {
        return null
    }

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, UserMessages> {
        return try {
            val currentPage = params.key ?: db.collection("message").limit(10).get().await()
            val lastVisibleProduct = currentPage.documents[currentPage.size() - 1]
            val nextPage =
                db.collection("message").startAfter(lastVisibleProduct).limit(10)
                    .get()
                    .await()

            LoadResult.Page(
                data = currentPage.toObjects(UserMessages::class.java),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}