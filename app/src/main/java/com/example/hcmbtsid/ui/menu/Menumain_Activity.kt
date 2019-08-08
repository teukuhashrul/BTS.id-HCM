package com.example.hcmbtsid.ui.menu

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.hcmbtsid.R
import com.google.android.material.navigation.NavigationView

class Menumain_Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle


    // initialize all the fragment here
    private var profile_Fragment: Profile_Fragment = Profile_Fragment()
    private var projects_Fragment: Projects_Fragment = Projects_Fragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menumain)

        // toolbar : yang ada diatas ada navigation drawer , text
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        // set action bar
        setSupportActionBar(toolbar)
        // remove title
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawer , toolbar ,  R.string.navigation_drawer_open , R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setHomeButtonEnabled(true)
        // set toolbar without title
        supportActionBar?.setDisplayShowTitleEnabled(false)
        changeFragment(profile_Fragment)
    }

    /**
     * Method override buat abis bikin menu di toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_action_menu,menu)
        return true
    }



    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            // handle  toolbar on click
            R.id.action_notification -> Toast.makeText(this, "Clicked notification", Toast.LENGTH_SHORT).show()
        }

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.nav_item_one -> {
                changeFragment(profile_Fragment)
                p0.setCheckable(true)
            }
            R.id.nav_item_two ->{
                changeFragment(projects_Fragment)
                p0.setCheckable(true)
            }
            R.id.nav_item_three -> {
                Toast.makeText(this, "Clicked item three", Toast.LENGTH_SHORT).show()
                p0.setCheckable(true)
            }
            R.id.nav_item_four -> {
                Toast.makeText(this, "Clicked item four", Toast.LENGTH_SHORT).show()
                p0.setCheckable(true)
            }
            R.id.nav_item_five ->{
                Toast.makeText(this, "Clicked item five", Toast.LENGTH_SHORT).show()
                p0.setCheckable(true)
            }
        }
        return true
    }


    fun changeFragment(fragmentTarget: Fragment): Unit{
        // fragment transaction
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragmentTarget)
        fragmentTransaction.commit()
    }
}
