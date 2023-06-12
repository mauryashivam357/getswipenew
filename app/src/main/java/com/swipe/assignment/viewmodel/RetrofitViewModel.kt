package com.swipe.assignment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swipe.assignment.retrofit.ApiService
import com.swipe.assignment.model.Product
import com.swipe.assignment.model.ProductAdd
import com.swipe.assignment.model.ProductResponse
import kotlinx.coroutines.launch

class RetrofitViewModel constructor(private val apiService: ApiService) : ViewModel() {
    var allProductList: MutableLiveData<Product> = MutableLiveData()
    var addProduct: MutableLiveData<ProductAdd> = MutableLiveData()
    val loadProduct = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<Boolean>()
    fun getProductFromAPI() {
        viewModelScope.launch {
            allProductList.value =
                apiService.getProduct().body()
        }
    }

    fun addProduct(product: ProductResponse) {
        viewModelScope.launch {
            addProduct.value = apiService.addProduct(
                product.product_name,
                product.product_type,
                product.price.toString(),
                product.tax.toString(),
                product.file
            ).body()
        }
    }
}