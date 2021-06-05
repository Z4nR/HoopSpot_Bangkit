package com.zulham.hoopspot.ui.place

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zulham.hoopspot.databinding.ActivityMainBinding
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class PlaceActivity : AppCompatActivity() {

    private lateinit var bindingPlace : ActivityMainBinding
    private var backPressed : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingPlace = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingPlace.root)

        supportFragmentManager.beginTransaction()
            .add(bindingPlace.fragmentPlaceContainer.id, PlaceFragment(), PlaceFragment::class.java.simpleName)
            .commit()

    }

    override fun onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            return
        } else {
            Toast.makeText(baseContext, "Press again to exit", Toast.LENGTH_SHORT).show()
        }
        backPressed = System.currentTimeMillis()
    }

}