package com.example.retrofitdemo

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    //to tell the retrofit that we are using retrofit
    @GET("demos/marvel")  //making a request at marvel end point
    //write a get request to make a function and will return call class of retrofit
    fun getData():Call<List<MyDataItem>>  //list of mydataitem
}