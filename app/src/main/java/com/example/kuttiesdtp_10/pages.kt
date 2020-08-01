package com.example.kuttiesdtp_10

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class SectionsPagerAdapter(private val context: addpro, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        Log.d("tab", "see")
        when (position) {
            0 -> (return Frag1())
            1 -> (return Frag2())
            else -> (return Frag1())
        }
    }


    override fun getPageTitle(position: Int): CharSequence? {

        when(position){
            0->(return "Edit Projects")
            1-> (return "Edit Members")
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}
