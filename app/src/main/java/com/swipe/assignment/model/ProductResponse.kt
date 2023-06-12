package com.swipe.assignment.model

import java.io.File

data class ProductResponse(
    val product_name:String,
    val product_type:String,
    val price:String,
    val tax:String,
    val file: List<File>
)