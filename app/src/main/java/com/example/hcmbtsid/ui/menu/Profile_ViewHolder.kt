package com.example.hcmbtsid.ui.menu

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.hcmbtsid.data.model.Profile_Model
import kotlinx.android.synthetic.main.profile_recycleview_item_row.view.*

class Profile_ViewHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener{
    //2
    private val itemAttribute = v.itemAttribute
    private val itemValue = v.itemValue
    private val itemImage = v.itemImage

    //3
    init {
        v.setOnClickListener(this)
    }

    //4
    override fun onClick(p0: View?) {
    }

    //5
    companion object{
        private val LOGO_KEY = "LOGO"
    }


    fun bindProfileItem(profile_Model: Profile_Model){
        itemAttribute.text = profile_Model.attribute
        itemValue.text = profile_Model.value
        itemImage.setImageResource(profile_Model.image)
    }
}