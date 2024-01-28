package com.example.preferenciascompartidas

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    companion object{
        const val SHARED_PREFS = "shared_prefs"
        const val EMAIL_KEY = "email_key"
        const val PASS_KEY = "password_key"
    }

    private lateinit var sharedPreferences: SharedPreferences
    private var email:String?=null
    private var pass:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPreferences=getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

        email=sharedPreferences.getString(EMAIL_KEY, null)
        pass=sharedPreferences.getString(PASS_KEY, null)

        val welcomeTV=findViewById<TextView>(R.id.TVWelcome)
        welcomeTV.text= getString(R.string.welcome)+" $email"
        val logoutBtn=findViewById<Button>(R.id.BtnLogout)
        logoutBtn.setOnClickListener{
            val editor=sharedPreferences.edit()

            editor.clear()

            editor.apply()

            val i=Intent(this@HomeActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}