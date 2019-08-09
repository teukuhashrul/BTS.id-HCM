package com.example.hcmbtsid.ui.editProfile

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.example.hcmbtsid.R
import com.example.hcmbtsid.data.model.Karyawan
import com.example.hcmbtsid.repository.DataRepository

import kotlinx.android.synthetic.main.activity_edit_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfile_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        setSupportActionBar(editProfile_Toolbar)

        // set on click for the navigation in the toolbar
        editProfile_Toolbar.setNavigationOnClickListener{
            finish()
        }

    }

    /**
     * Method after we create menu xml for action bar in the up right
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_action_editprofile,menu)
        return true
    }

    /**
     * Method that called to handle on click for the menu in the up right
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            // handle  toolbar on click
            R.id.action_editProfile -> submitEditProfile()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigateUp(): Boolean {
        return super.onNavigateUp()
    }

    fun submitEditProfile(){
        //create dummy data
        val karyawan = Karyawan(2 ,"Husnul" , "husnul@gmail.com" , "jakarta",
            "1989-12-10T00:00:00.000Z" , "jl dangdeur indah banget" ,"123123123" ,
            "123213", "123123" , "2019-07-19T00:00:00.000Z" , "2019-08-02T00:00:00.000Z",
            "null" , "sadsfdfdfsd" , 3 , 3 , 3 , "jl kadieu" , 12)


        val userServices = DataRepository.create()
        userServices.editKaryawan(2 , karyawan).enqueue(object : Callback<Karyawan> {

            override fun onResponse(call: Call<Karyawan>, response: Response<Karyawan>) {
                Toast.makeText(applicationContext , "Response : ${response.body()}" , Toast.LENGTH_LONG ).show()
            }

            override fun onFailure(call: Call<Karyawan>, t: Throwable) {
                Toast.makeText(applicationContext , " Error : ${t.message}" , Toast.LENGTH_LONG).show()
            }
        })

    }
}
