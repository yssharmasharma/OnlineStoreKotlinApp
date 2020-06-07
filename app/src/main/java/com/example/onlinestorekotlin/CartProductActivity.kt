package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_product.*

class CartProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_product)

        var cartactivityUrl="http://192.168.43.181/OnlineStoreApp/fetch_temporary_order.php?email=${Person.email}"
        var cartProductList=ArrayList<String>()
        var RequestQ=Volley.newRequestQueue(this)
        var jsonArr=JsonArrayRequest(Request.Method.GET,cartactivityUrl,null,Response.Listener {
            response ->

            for (joIndex in 0.until(response.length())){


                cartProductList.add("Product ID=${response.getJSONObject(joIndex).getInt("id")}"
                        +"\n NAME = ${response.getJSONObject(joIndex).getString("name")}"
                        +"\n PRICE = ${response.getJSONObject(joIndex).getInt("price")}"
                        +"\n EMAIL = ${response.getJSONObject(joIndex).getString("email")}"
                        +"\n AMOUNT = ${response.getJSONObject(joIndex).getInt("amount")}")

            }
            var cartproductadapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,cartProductList)
            cartProductsListView.adapter=cartproductadapter


        },Response.ErrorListener {
            error ->
        })
        RequestQ.add(jsonArr)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item?.itemId==R.id.continueshoppingitem){

            var intent=Intent(this,HomeScreen::class.java)
            startActivity(intent)
        }
        else if(item?.itemId==R.id.cancel){

            var deleteUrl="http://192.168.43.181/OnlineStoreApp/decline_order.php?email=${Person.email}"
            var RequesQ=Volley.newRequestQueue(this)
            var stringRequest=StringRequest(Request.Method.GET,deleteUrl,Response.Listener {
                response ->

                var intent=Intent(this,HomeScreen::class.java)
                startActivity(intent)
            },Response.ErrorListener {
                error ->
            })
            RequesQ.add(stringRequest)
        }
        else if (item?.itemId == R.id.verifyitem) {


            var verifyOrderUrl = "http://192.168.43.181/OnlineStoreApp/verify_order.php?email=${Person.email}"
            var requestQ = Volley.newRequestQueue(this)
            var stringRequest = StringRequest(Request.Method.GET, verifyOrderUrl, Response.Listener {
                    response ->


                var intent = Intent(this, FinalizeShoppingActivity::class.java)
                Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                intent.putExtra("LATEST_INVOICE_NUMBER", response)
                startActivity(intent)


            }, Response.ErrorListener { error ->  })



            requestQ.add(stringRequest)
        }



        return super.onOptionsItemSelected(item)
    }

}
