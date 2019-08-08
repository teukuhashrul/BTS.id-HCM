package com.example.hcmbtsid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hcmbtsid.R
import com.example.hcmbtsid.data.model.Profile_Model
import com.example.hcmbtsid.ui.menu.Profile_ViewHolder

class Profile_RecyclerAdapter(private val profiles: List<Profile_Model>) : RecyclerView.Adapter<Profile_ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Profile_ViewHolder {
        return Profile_ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.profile_recycleview_item_row,parent,false))

    }

    override fun getItemCount(): Int = profiles.size

    override fun onBindViewHolder(viewHolder: Profile_ViewHolder, position: Int) {
         viewHolder.bindProfileItem(profiles[position])
    }

}