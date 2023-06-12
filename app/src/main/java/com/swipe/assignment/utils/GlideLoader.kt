package com.swipe.assignment.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.swipe.assignment.R
import java.io.IOException


class GlideLoader(private val context: Context) {

    fun loadUserPicture(image: Uri, imageView: ImageView){

        try{
            Glide
                .with(context)
                // .load(Uri.parse(imageURI.toString()))
                .load(image) //uri of the image
                .centerCrop()
                .placeholder(R.drawable.ic_add_image)
                .into(imageView)
        }catch (e: IOException){
            e.printStackTrace()
        }

    }

    }