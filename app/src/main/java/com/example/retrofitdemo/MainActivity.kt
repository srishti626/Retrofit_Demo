package com.example.retrofitdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.widget.Adapter
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){
    lateinit var myAdapter:MyAdapter
    lateinit var linearLayoutManager:LinearLayoutManager
    private var rcView:RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //it will not change its size to other layout overlapping them
        rcView= findViewById(R.id.RCView)
        rcView?.setHasFixedSize(true)
        linearLayoutManager=LinearLayoutManager(this)
        rcView?.layoutManager=linearLayoutManager

        getMyData()
    }

    private fun getMyData() {
        //make a retrofit builder object
        val retrofitBuilder=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())   //add the convertor factory
            .baseUrl("https://simplifiedcoding.net/")  //add the base url
            .build()
            .create(ApiInterface::class.java)  //reference of this interface

        //variable to get the data from the retrofit builder
        val retroFit=retrofitBuilder.getData()  //interface function where we need to get the data items
        //serializable->can be used here not to get confuse with names
        retroFit.enqueue(object : Callback<List<MyDataItem>?> {
            //ctrl+shift+space
            //response getting from url will hold the response
            override fun onResponse(call: Call<List<MyDataItem>?>, response: Response<List<MyDataItem>?>) {
                    val responseBody=response.body()!!
                Log.e("response",responseBody.toString())
                //send the response to myadapter
                myAdapter=MyAdapter(baseContext,responseBody) //send response to myadapter
                myAdapter.notifyDataSetChanged()
                rcView?.adapter=myAdapter    //set the adapter with the recycler view

                //create a reference variable that will point to the adapter
                myAdapter.setOnCLickListener(object :MyAdapter.onItemClickedListener{
                    override fun onItemCLicked(position: Int) {
                        //get the positoin of the item of any recycler view
                        //Toast.makeText(this@MainActivity,"u clicked here",Toast.LENGTH_SHORT).show()

                        //intent to move from one activity to another activity
                        //pass the rest of the values of the json objects by putExtras
                        val intent=Intent(this@MainActivity,ProfileDescription::class.java)
                        intent.putExtra("list",responseBody[position])
//                        intent.putExtra("Team",responseBody[position].team)
//                        intent.putExtra("About",responseBody[position].bio)
//                        intent.putExtra("Publisher",responseBody[position].publisher)
//                        intent.putExtra("Created",responseBody[position].createdBy)
//                        intent.putExtra("First",responseBody[position].firstAppearance)
                        startActivity(intent)
                    }
                })
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity","onFailure: "+ t.message)
            }
        })
    }
}