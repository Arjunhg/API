package com.example.api
//Creating kotlin interface
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    //creating a function

    @GET("products")//have to write end point of the api you are using(here used the last word of website address).
    fun getProductData() : Call<MyData>

}