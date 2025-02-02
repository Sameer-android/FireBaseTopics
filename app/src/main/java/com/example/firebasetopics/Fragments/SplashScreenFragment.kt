package com.example.firebasetopics.Fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firebasetopics.R
import com.example.firebasetopics.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    private lateinit var binding: FragmentSplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_splash_screen, container, false)
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)

        onChangeColor()
        return binding.root
    }

    private fun onChangeColor() {
        Handler(Looper.getMainLooper()).postDelayed({
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main, LogInFragment()).commit()
        }, 2000)
        val welcomeText = "Welcome"
        val spannableString = SpannableString(welcomeText)
        spannableString.setSpan(ForegroundColorSpan(Color.parseColor("#FF0000")), 0, 5, 0)
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#312222")),
            5,
            welcomeText.length,
            0
        )
        binding.welcomeText.text = spannableString

//        binding.welcomeText.text = Html.fromHtml("<font color=${Color.parseColor("#FF0000")}>Welco</font> " +
//        "<font color=${Color.parseColor("#312222")}>me</font>")
    }

}