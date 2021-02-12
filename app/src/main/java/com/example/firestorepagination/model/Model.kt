package com.example.firestorepagination.model

import com.google.firebase.firestore.PropertyName

data class UserMessages(

    @PropertyName("message")
    val message: String = "",

    @PropertyName("timestamp")
    val timestamp: Long = 0,

    @PropertyName("imageUrl")
    val imageUrl: String = "",

    @PropertyName("id")
    val id: String = "",

    @PropertyName("upVoteCount")
    val upVoteCount: Long = 0,

    @PropertyName("postCommentCount")
    val postCommentCount: Long = 0,

    @PropertyName("userName")
    val userName: String = "",

    @PropertyName("type")
    val type: String = ""
)
