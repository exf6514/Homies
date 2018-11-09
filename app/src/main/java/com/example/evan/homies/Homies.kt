package com.example.evan.homies

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_homies.*
import kotlinx.android.synthetic.main.fragment_login.*

var userId: Int? = null

class Homies : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        // get the selected item and replace the fragment
        when (item.itemId) {
            R.id.navigation_chores -> selectedFragment = ChoresFragment()
            R.id.navigation_house -> selectedFragment = HouseFragment()
            R.id.navigation_profile -> selectedFragment = ProfileFragment()
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment!!).commit()
         true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO: get global user id? If not logged in, send to login fragment

        if(userId == null) {
            // send to login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            //by default, set the fragment to chore list
            setContentView(R.layout.activity_homies)
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, ChoresFragment()).commit()
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }
    }

}
