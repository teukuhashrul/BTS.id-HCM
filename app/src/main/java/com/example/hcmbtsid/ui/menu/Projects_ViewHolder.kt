package com.example.hcmbtsid.ui.menu

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.hcmbtsid.R
import com.example.hcmbtsid.data.model.MyProjects
import com.example.hcmbtsid.adapter.Projects_RecyclerAdapter
import kotlinx.android.synthetic.main.projects_recycleview_item_row.view.*

class Projects_ViewHolder(v: View): RecyclerView.ViewHolder(v){
    private val item_ProjectsName = v.item_ProjectsName
//    private val item_StartDate = v.item_startDate
//    private val item_EndDate = v.item_EndDate
//    private val item_ClientName = v.item_ClientName
    private var item_Status = v.item_Status


    // button for detail
    private val iv_Detail = v.detail_iv



    fun bindProjectsItem(myProjects: MyProjects, listener: Projects_RecyclerAdapter.OnItemClickListener){
        item_ProjectsName.text = myProjects.projectsName
//        item_StartDate.text = myProjects.startDate
//        item_EndDate.text = myProjects.endDate
//        item_ClientName.text = myProjects.clientName
        item_Status.text = myProjects.status
        determineStatus(myProjects.status)
        iv_Detail.setOnClickListener(View.OnClickListener {
            listener.onItemClick(myProjects)
        })
    }

    /**
     * function to determine the status color in the UI get from the project status
     *  IF on progress then change the color into imperial red
     *  IF completed then change the color into limegreen
     *
     *  call the wrapper methond change Status !
     *
     */
    fun determineStatus(status: String){
        when(status){
            "On Progress" -> change_Status(R.drawable.rounded_status_onprogress, "#ed2939")
            "Completed"   -> change_Status(R.drawable.rounded_status_completed, "#32CD32")
        }

    }

    /**
     *
     */
    fun change_Status(background: Int, colorCode: String){
        item_Status.setBackgroundResource(background)
        item_Status.setTextColor(Color.parseColor(colorCode))
    }


}