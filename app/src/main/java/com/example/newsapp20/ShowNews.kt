package com.example.newsapp20

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class ShowNews : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_news)

        //val text1:TextView = findViewById<TextView>(R.id.newsFeed)

        val tabLayout:TabLayout = findViewById(R.id.tabs)
        val vPager:ViewPager = findViewById(R.id.viewPager)

        tabLayout.setupWithViewPager(vPager)

        val pageAdapter = PageAdapter(supportFragmentManager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        pageAdapter.addFragment(generalFrag(),"General")
        pageAdapter.addFragment(technologyFrag(),"Technology")
        pageAdapter.addFragment(fSports(),"Sports")
        pageAdapter.addFragment(fScinece(),"Science")
        pageAdapter.addFragment(fEntertainment(),"Entertainment")
        pageAdapter.addFragment(fHealth(),"Health")
        pageAdapter.addFragment(fBusiness(),"Business")

        vPager.adapter = pageAdapter
    }
}