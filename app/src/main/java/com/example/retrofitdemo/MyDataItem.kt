package com.example.retrofitdemo

import java.io.Serializable

data class MyDataItem(
    val name: String,
    val realname: String,
    val imageurl: String,
    val bio: String,
    val createdby: String,
    val firstappearance: String,
    val publisher: String,
    val team: String
) : Serializable