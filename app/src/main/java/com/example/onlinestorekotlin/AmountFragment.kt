package com.example.onlinestorekotlin


import android.app.Activity
import android.app.Dialog
import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

/**
 * A simple [Fragment] subclass.
 */
class AmountFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var fragmentView= inflater.inflate(R.layout.fragment_amount, container, false)

        var edtEnterAmount=fragmentView.findViewById<EditText>(R.id.edtEnterAmount)
        var btnAddtoCart=fragmentView.findViewById<ImageButton>(R.id.btnAddtoCart)

        btnAddtoCart.setOnClickListener {
            var cartUrl="http://192.168.43.181/OnlineStoreApp/insert_temporary_order.php?email=${Person.email}&product_id=${Person.addToCartProductID}&amount=${edtEnterAmount.text.toString()}"

            var RequestQ=Volley.newRequestQueue(activity)
            var stringRequest=StringRequest(Request.Method.GET,cartUrl,Response.Listener {
                response ->

                var intent=Intent(activity,CartProductActivity::class.java)
                startActivity(intent)


            },Response.ErrorListener {
                error ->
            })
            RequestQ.add(stringRequest)
        }
        return fragmentView
    }


}
