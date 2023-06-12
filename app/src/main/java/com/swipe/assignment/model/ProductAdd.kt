package com.swipe.assignment.model

import java.io.File

data class ProductAdd(
    val massage:String,
    val product_details: ProductResponse,
    val product_id:Int,
    val success:Boolean
 )