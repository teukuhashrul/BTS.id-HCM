package com.example.hcmbtsid.ui.editProfile

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.example.hcmbtsid.R

import kotlinx.android.synthetic.main.activity_edit_profile.*

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
            R.id.action_editProfile -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigateUp(): Boolean {
        return super.onNavigateUp()
    }
}
