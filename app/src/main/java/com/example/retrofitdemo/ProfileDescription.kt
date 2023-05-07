package com.example.retrofitdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import org.w3c.dom.Text

class ProfileDescription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_description)
        //refer to all the elements in the view of json objects
        val team:TextView=findViewById(R.id.txt1)
        val about:TextView=findViewById(R.id.txt2)
        val pub:TextView=findViewById(R.id.txt3)
        val created:TextView=findViewById(R.id.txt4)
        val first:TextView=findViewById(R.id.txt5)
        //initialised a list of serializable in my data item
        var list = intent.getSerializableExtra("list") as MyDataItem
        //just to print the content
        Log.e("team###",list.team)


//        //get our data in this bundle
//        val bundle:Bundle?=intent.extras
//        val Team=bundle!!.getString("Team")
//        val About=bundle!!.getString("About")
//        val Publisher=bundle!!.getString("Publisher")
//        val Created=bundle!!.getString("Created")
//        val First=bundle!!.getString("First")

        //set the data to our view elements
        team.text=list.team
        about.text=list.bio
        pub.text=list.publisher
        created.text=list.createdby
        first.text=list.firstappearance
    }
}