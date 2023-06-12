package com.swipe.assignment.view.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.swipe.assignment.view.fragments.ProductFragment
import com.swipe.assignment.R
import com.swipe.assignment.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            val fm: FragmentManager = this.supportFragmentManager
            val fragment = ProductFragment()
            fm.beginTransaction()
          //      .add(R.id.main_contenier, fragment).commit()
            .replace(R.id.main_contenier,fragment).commit()


    }
}