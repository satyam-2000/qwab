package com.internshala.qwebs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var signUptext:TextView
    lateinit var logEmail:EditText
    lateinit var logPass:EditText
    lateinit var loginButton:Button
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth= FirebaseAuth.getInstance()
        logEmail=findViewById(R.id.logEmail)
        logPass=findViewById(R.id.logPass)
        loginButton=findViewById(R.id.loginButton)
        signUptext=findViewById(R.id.signUptext)
        supportActionBar?.hide()
        loginButton.setOnClickListener {
            userLogin()
        }
        signUptext.setOnClickListener {
            val intent= Intent(this,Signup::class.java)
            startActivity(intent)
        }
    }

    private fun userLogin(){
        val email:String=logEmail.text.toString()
        val password:String=logPass.text.toString()
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                val intent= Intent(this,Home::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Incorrect Email or Password",Toast.LENGTH_SHORT).show()
            }
        }
    }
}