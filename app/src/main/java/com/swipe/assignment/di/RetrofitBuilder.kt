package com.swipe.assignment.di

import com.swipe.assignment.retrofit.ApiService
import com.swipe.assignment.utils.Constants
import com.swipe.assignment.viewmodel.RetrofitViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val api: ApiService = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiService::class.java)
fun provideJson() : GsonConverterFactory = GsonConverterFactory.create()
fun provideApi(gsonConverterFactory: GsonConverterFactory):ApiService = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(gsonConverterFactory)
    .build()
    .create(ApiService::class.java)

//fun provideMoshi():Moshi = Moshi
//    .Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
//fun provideApiService(moshi: Moshi):ApiService = Retrofit
//    .Builder()
//    .run {
//    baseUrl(BASE_URL)
//    addConverterFactory(MoshiConverterFactory.create(moshi))
//    build()
//}.create(ApiService::class.java)

val retrofitBuilderModule = module {
//    single { provideMoshi() }
//    single { provideApiService(get())}
    single { provideJson() }
    single { provideApi(get()) }
    viewModel { RetrofitViewModel(get())}
}