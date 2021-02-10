package com.example.firestorepagination.model

data class UserMessages(
    val message: String,
    val timestamp: Long,
    val imageUrl: String,
    val id: String,
    val upVoteCount: Long,
    val postCommentCount: Long,
    val userName: String,
    val type: String
)
