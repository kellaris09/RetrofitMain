package com.example.retrofitproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitproject.adapter.ProductAdapter
import com.example.retrofitproject.databinding.ActivityMainBinding
import com.example.retrofitproject.retrofit.MainAPI
import com.example.retrofitproject.retrofit.User
import com.example.retrofitproject.retrofit.authRequest
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ProductAdapter()
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = adapter








//        val tv = findViewById<TextView>(R.id.firstName)
//        val b = findViewById<Button>(R.id.button)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()



        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val MainAPI = retrofit.create(MainAPI::class.java)



        var user: User? = null



        binding.sv.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                CoroutineScope(Dispatchers.IO).launch {
                    val list = newText?.let { MainAPI.getProductsByNameAuth(user?.token ?:"", it) }



                    runOnUiThread {
                        binding.apply {
                            adapter.submitList(list?.products)

                        }
                    }
                }

                return true

            }

        })





        }
    }
