package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_Loginbtn.setOnClickListener {
            val LoginUrl="http://192.168.43.181/OnlineStoreApp/login_app_user.php?email="+
                    activity_main_edtEmailtxt.text.toString()+
                    "&pass="+activity_main_edtPasstxt.text.toString()

            val RequestQ=Volley.newRequestQueue(this)
            val stringrequest=StringRequest(Request.Method.GET,LoginUrl,Response.Listener { 
                response ->  
            if (response.equals("The user does exist")){

                Person.email=activity_main_edtEmailtxt.text.toString()
                Toast.makeText(this,response,Toast.LENGTH_SHORT).show()
                val homeintent=Intent(this,HomeScreen::class.java)
                startActivity(homeintent)

            }
                else{

                val dialogBuilder=AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message")
                dialogBuilder.setMessage(response)
                dialogBuilder.create().show()
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

        activity_main_SignUpbtn.setOnClickListener {
            var signupIntent=Intent(this,SignUp_Layout::class.java)
            startActivity(signupIntent)
        }


    }
}
