package com.example.onlinestorekotlin

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        var brands_url="http://192.168.43.181/OnlineStoreApp/fetch_brands.php"
        var brandlist=ArrayList<String>()
        var requestQ=Volley.newRequestQueue(this)

        var jsonArr=JsonArrayRequest(Request.Method.GET,brands_url,null,Response.Listener {
            response ->

            for (jsonObject in 0.until(response.length())){

                brandlist.add(response.getJSONObject(jsonObject).getString("brand"))
            }

            var brandListAdapter=ArrayAdapter(this,R.layout.brand_item_textview,brandlist)
            brands_listview.adapter=brandListAdapter




        },Response.ErrorListener {
            error ->

            val dialogBuilder=AlertDialog.Builder(this)
            dialogBuilder.setTitle("Message")
            dialogBuilder.setMessage(error.message)
            dialogBuilder.create().show()
        })
        requestQ.add(jsonArr)

        brands_listview.setOnItemClickListener { adapterView, view, i, l ->

            val tappedBrand=brandlist.get(i)
            val intent=Intent(this,FetchEProducts::class.java)
            intent.putExtra("BRAND",tappedBrand)
            startActivity(intent)
        }
    }
}
