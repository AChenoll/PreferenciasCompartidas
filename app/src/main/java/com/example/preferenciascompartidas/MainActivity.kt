package com.example.preferenciascompartidas

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.preferenciascompartidas.R.*
import com.example.preferenciascompartidas.R.id.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val SHARED_PREFS="shared_prefs"
        const val EMAIL_KEY="email_key"
        const val PASS_KEY="password key"
    }

    private lateinit var sharedPreferences: SharedPreferences
    private var email:String?=null
    private var password:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        sharedPreferences=getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)

        val emailEdt=findViewById<EditText>(EdtEmail)
        val passwordEdt=findViewById<EditText>(EdtPassword)
        val loginBtn=findViewById<Button>(BtnLogin)

        email=sharedPreferences.getString(EMAIL_KEY,null)
        password=sharedPreferences.getString(PASS_KEY,null)

        loginBtn.setOnClickListener{
            if(TextUtils.isEmpty(emailEdt.text.toString())&& TextUtils.isEmpty(passwordEdt.text.toString())){
                Toast.makeText(this@MainActivity, "@string/empty", Toast.LENGTH_SHORT).show()
            } else {
                val editor=sharedPreferences.edit()

                editor.putString(EMAIL_KEY,emailEdt.text.toString())
                editor.putString(PASS_KEY, passwordEdt.text.toString())

                editor.apply()

                val i=Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(email != null && password != null){
            val i= Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(i)
        }
    }
}