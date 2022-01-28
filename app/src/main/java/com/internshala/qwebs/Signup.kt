package com.internshala.qwebs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var singEmail:EditText
    lateinit var singPassword:EditText
    lateinit var singConfPassword:EditText
    lateinit var signUpButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth= FirebaseAuth.getInstance()
        signUpButton=findViewById(R.id.signUpButton)
        singEmail=findViewById(R.id.singEmail)
        singPassword=findViewById(R.id.singPassword)
        supportActionBar?.hide()
        singConfPassword=findViewById(R.id.singConfPassword)
        signUpButton.setOnClickListener {
            signUpUser()
        }
    }

    private fun signUpUser(){
        val email:String=singEmail.text.toString()
        val password:String=singPassword.text.toString()
        val confirm:String=singConfPassword.text.toString()

        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this,"Email and password can't be blank",Toast.LENGTH_SHORT).show()
            return
        }

        if(password!=confirm){
            Toast.makeText(this,"Password and Confirm Password do not match",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                 Toast.makeText(this,"Sign up seccessful",Toast.LENGTH_SHORT).show()
                 val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Error Creating User",Toast.LENGTH_SHORT).show()
                }
            }

    }
}