package com.example.evan.homies

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_homies.*
import kotlinx.android.synthetic.main.fragment_login.*


class Homies : AppCompatActivity() {

    private var userId: Long? = null
    private var userName: String? = null
    private var sharedPreferences: SharedPreferences? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        // get the selected item and replace the fragment
        when (item.itemId) {
            R.id.navigation_chores -> selectedFragment = ChoresFragment()
            R.id.navigation_house -> selectedFragment = HouseFragment()
            R.id.navigation_profile -> selectedFragment = ProfileFragment()
        }

        var args = Bundle()
        args.putLong("user_id", userId!!)
        args.putString("user_name", userName!!)

        selectedFragment!!.arguments = args
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
         true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("preferences",Context.MODE_PRIVATE)
        userId = sharedPreferences?.getLong("USER_ID",0)
        userName = sharedPreferences?.getString("USER_NAME", "Unknown")

        if(userId!! == 0.toLong() || userName!! == "Unknown") {
            // send to login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            //by default, set the fragment to chore list
            setContentView(R.layout.activity_homies)

            val fragment = ChoresFragment()
            val args = Bundle()
            args.putLong("user_id", userId!!)
            args.putString("user_name", userName!!)

            fragment.arguments = args
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.logout) {
            sharedPreferences?.edit()?.remove("USER_ID")?.remove("USER_NAME")?.apply()
            startActivity(Intent(this, LoginActivity::class.java))
            //Android method to destroy the current activity.. I believe its another form of
            //dealing with the backstack
            finish()
        }

        return true
    }

}
