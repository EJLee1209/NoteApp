package com.dldmswo1209.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dldmswo1209.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}