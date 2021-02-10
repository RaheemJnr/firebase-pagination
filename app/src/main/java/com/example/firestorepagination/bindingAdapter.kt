package com.example.firestorepagination

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.firestorepagination.model.UserMessages
import com.example.firestorepagination.ui.convertLongToDateString


//binding for messages
@BindingAdapter("messages")
fun TextView.setUserMessage(Item: UserMessages?) {
    Item?.let {
        text = Item.message
    }
}

//binding for upvote count
@BindingAdapter("upVoteCount")
fun TextView.setupVoteCount(Item: UserMessages?) {
    Item?.let {
        text = Item.upVoteCount.toString()
    }
}

//binding for reaction
@BindingAdapter("commentCount")
fun TextView.setCommentCount(Item: UserMessages?) {
    Item?.let {
        text = Item.postCommentCount.toString()
    }
}

//binding for date posted
@BindingAdapter("date")
fun TextView.setDate(Item: UserMessages?) {
    Item?.let {
        text = convertLongToDateString(Item.timestamp)
    }
}


//binding for images
/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView?, url: String?) {
    if (imageView != null) {
        url?.let {
            val toImageUri = it.toUri().buildUpon().scheme("https").build()
            Glide.with(imageView.context)
                .load(toImageUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_connection_error)
                )
                .into(imageView)
        }
    }
}