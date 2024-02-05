package com.example.retrofitproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitproject.databinding.ContentBaseBinding


class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ContentBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ContentBaseBinding.inflate(getLayoutInflater())

        setContentView(binding.root)

    }

}