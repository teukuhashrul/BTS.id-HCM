package com.example.hcmbtsid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hcmbtsid.R
import com.example.hcmbtsid.data.model.MyProjects
import com.example.hcmbtsid.ui.menu.Projects_ViewHolder

class Projects_RecyclerAdapter(private val projectsList: List<MyProjects>, private val listener: OnItemClickListener): RecyclerView.Adapter<Projects_ViewHolder>(){

    public interface OnItemClickListener{
        fun onItemClick(myProjects: MyProjects)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Projects_ViewHolder {
        return Projects_ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.projects_recycleview_item_row,parent,false))
    }

    override fun getItemCount(): Int = projectsList.size

    override fun onBindViewHolder(holder: Projects_ViewHolder, position: Int) {
       holder.bindProjectsItem(projectsList[position], listener)
    }

}