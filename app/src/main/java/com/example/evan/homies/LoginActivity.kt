package com.example.evan.homies

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportFragmentManager.beginTransaction()
            .add(R.id.login_fragment, LoginFragment())
            .commit()
    }

    fun logIn(v: View){

        val inflator: LayoutInflater = layoutInflater

        val email:String = emailEditText.text.toString()
        val password:String = passwordEditText.text.toString()

        Toast.makeText(this, "Hello $email", Toast.LENGTH_LONG).show()

        //TODO: change this is real userId after fetch
        userId = 1
        val intent = Intent(this, Homies::class.java)
        startActivity(intent)
    }

    fun goToCreateAccount(v: View) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.login_fragment, RegisterFragment())
            .addToBackStack(null)
            .commit()
    }

}
