package com.example.kuttiesdtp_10

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.addproj.*


class addpro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("tabb", "vandu")
        setContentView(R.layout.addproj)

        viewpager.adapter = SectionsPagerAdapter(this, supportFragmentManager)
        tabLayout.setupWithViewPager(viewpager)
    }


}
