package com.example.firebasetopics.Fragments

import androidx.fragment.app.Fragment
import com.example.firebasetopics.R

open class BaseFragment : Fragment() {
    fun replaceFragment(fragment: Fragment) {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.main, fragment)
            .addToBackStack(null)
            .commit()
    }
}