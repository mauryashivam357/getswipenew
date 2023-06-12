package com.swipe.assignment.retrofit

import com.swipe.assignment.model.Product
import com.swipe.assignment.model.ProductAdd
import com.swipe.assignment.utils.Constants
import retrofit2.Response
import retrofit2.http.*
import java.io.File

interface ApiService {

    @GET(Constants.END_POINT_GET)
    suspend fun getProduct():Response<Product>

    @FormUrlEncoded
    @POST(Constants.END_POINT_ADD)
    @JvmSuppressWildcards
    suspend fun addProduct(
        @Field("product_name") productName:String,
        @Field("product_type") productType:String,
        @Field("price") price:String,
        @Field("tax") tax:String,
        @Field("files[]") file:List<File>
    ):Response<ProductAdd>
}