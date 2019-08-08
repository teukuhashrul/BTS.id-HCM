package com.example.hcmbtsid.ui.projectDetail

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.example.hcmbtsid.R

import kotlinx.android.synthetic.main.activity_project_detail_.*

class ProjectDetail_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail_)
        setSupportActionBar(projectDetail_Toolbar)

        // disable title default , because using custom text view in the layout
        supportActionBar?.setDisplayShowTitleEnabled(false)

        projectDetail_Toolbar.setNavigationOnClickListener{
            finish()
        }
    }

}
