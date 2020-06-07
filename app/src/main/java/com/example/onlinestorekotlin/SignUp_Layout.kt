package com.example.onlinestorekotlin

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up__layout.*

class SignUp_Layout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up__layout)

        signup_Signupbtn.setOnClickListener {
            if (signup_edtPasstxt.text.toString().equals(signup_edtPassAgaintxt.text.toString())){
                //register process
                val SignupUrl="http://192.168.43.181/OnlineStoreApp/join_new_user.php?email="+signup_edtEmailtxt.text.toString()+
                        "&username="+signup_edtUsernametxt.text.toString()+
                        "&pass="+signup_edtPasstxt.text.toString()
                val RequestQ=Volley.newRequestQueue(this)
                val stringrequest=StringRequest(Request.Method.GET,SignupUrl,Response.Listener {
                    response ->

                    if (response.equals("A User with this Email Address already exists")){

                        val dialogBuilder=AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Message")
                        dialogBuilder.setMessage(response)
                        dialogBuilder.create().show()

                    }else{

                        Person.email=signup_edtEmailtxt.text.toString()

                        Toast.makeText(this,response,Toast.LENGTH_SHORT).show()
                        val homeintent=Intent(this,HomeScreen::class.java)
                        startActivity(homeintent)



                    }



                },Response.ErrorListener {
                    error ->

                    val dialogBuilder=AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message")
                    dialogBuilder.setMessage(error.message)
                    dialogBuilder.create().show()
                })
                RequestQ.add(stringrequest)



            }
            else {

                val dialogBuilder= AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage("Password Mismatch")
                dialogBuilder.create().show()

            }
        }
        signup_Loginbtn.setOnClickListener {
            finish()
        }
    }
}
