package com.example.hcmbtsid.ui.changePassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hcmbtsid.R
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
//        forgot_login.setOnClickListener{
////           val intent = intent
////            Toast.makeText(this," "+ intent.getIntExtra("MANTAP",0) , Toast.LENGTH_SHORT).show()
//        }

        changePassword_Toolbar.setNavigationOnClickListener{
            finish()
        }
    }
}
