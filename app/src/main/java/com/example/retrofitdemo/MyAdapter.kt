package com.example.retrofitdemo

import android.content.Context
import android.hardware.biometrics.BiometricManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class MyAdapter(val context:Context,val userList: List<MyDataItem> ):
    RecyclerView.Adapter<MyAdapter.Viewholder>() {
    //create a variable the same interface that we have just created
    private lateinit var mlistener:onItemClickedListener
//
    //step-1
    //create an interface
    interface onItemClickedListener {
        //declare an function and argument as position of the item on which user has clicked
        fun onItemCLicked(position: Int) {
        }
    }

    //take the object of this interface
    fun setOnCLickListener(listener: onItemClickedListener){
        mlistener=listener  //set the value to value that is passed in the arguments
    }
    //extend the recycler view adapter and generics will be class of viewholder
    class Viewholder(item_view:View,listener: onItemClickedListener):RecyclerView.ViewHolder(item_view) { //extends the recyclerview
        //to show name and realname and image in our recycler view
        var Name:TextView
        var RealName:TextView
        val Image:ImageView

        init{ //used to initialise the variables as a constructor
            Name=item_view.findViewById(R.id.name)
            RealName=item_view.findViewById(R.id.realname)
            Image=item_view.findViewById(R.id.imageView)


            //set the onclicklistener on item view and pass the adapter position
          item_view.setOnClickListener {
              listener.onItemCLicked(adapterPosition)
          }

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemView=LayoutInflater.from(context).inflate(R.layout.item_view,parent,false)
        return Viewholder(itemView,mlistener)  //pass the mlistener too
    }
    //size of the userlist and return it
    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        //bind the view in the recyclerView
        Picasso.get().load(userList[position].imageurl).into(holder.Image) //picasso
        holder.Name.text=userList[position].name.toString()  //kotlin use text
        holder.RealName.text=userList[position].realname.toString()
    }


}