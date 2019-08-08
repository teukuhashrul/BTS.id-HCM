package com.example.hcmbtsid.ui.forgotPassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hcmbtsid.R
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        forgot_login.setOnClickListener{
           val intent = intent
            Toast.makeText(this," "+ intent.getIntExtra("MANTAP",0) , Toast.LENGTH_SHORT).show()
        }
    }
}
