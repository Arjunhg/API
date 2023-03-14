package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()



            recyclerView = findViewById(R.id.recyclerView)

            val retrofitBuilder = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())//Here only gson is used
                .build()
                .create(ApiInterface::class.java)

            val retrofitData = retrofitBuilder.getProductData() //method from api interface.Now we will use this data in next steps

            retrofitData.enqueue(object : Callback<MyData?> { //While calling for an api there can be two scenario: will execute or will fail. use ctrl+shift+space

                override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                    // if api call is a success, then use the data of API and show in your app
                    var responseBody = response.body()
                    val productList = responseBody?.products!!

                    /* val collectDataInSB = StringBuilder()

                    for(myData in productList){
                        collectDataInSB.append(myData.title + " ")
                    }
                    val tv = findViewById<TextView>(R.id.textView)
                    tv.text = collectDataInSB */

                    //Now Setting adapter in recycler view

                    myAdapter = MyAdapter(this@MainActivity, productList)
                    recyclerView.adapter = myAdapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                }

                override fun onFailure(call: Call<MyData?>, t: Throwable) {
                    // if api call fails
                    Log.d("Main Activity ", "onFailure: " + t.message)
                }
            })

        }
}