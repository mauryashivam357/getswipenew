package com.swipe.assignment.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.activity.result.ActivityResultLauncher

object Constants {
    const val READ_STORAGE_PERMISSION_CODE: Int = 1
    const val BASE_URL = "https://app.getswipe.in/"
    const val END_POINT_GET = "api/public/get"
    const val END_POINT_ADD ="api/public/add"

    fun showImageChooser(resultLauncher: ActivityResultLauncher<Intent>){
        // an intent for launching the image selection of phone storage.
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        // launches the image selection of phone storage using the constant code
     //   activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
        resultLauncher.launch(galleryIntent)
    }
}