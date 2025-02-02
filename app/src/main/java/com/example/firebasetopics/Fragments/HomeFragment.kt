package com.example.firebasetopics.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firebasetopics.R
import com.example.firebasetopics.databinding.FragmentHomeBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        showUserEmail()
        onClickListener()
        return binding.root
    }

    private fun showUserEmail() {
            // Get the current user from FirebaseAuth
            val user = FirebaseAuth.getInstance().currentUser
            user?.let {
                // Get and display the user's email in a TextView
                val email = it.email
                val name = it.displayName
                binding.emailTextView.text = "User Email: $email"
                binding.nameTextView.text = "User Name: $name"
            }
        }


    private fun onClickListener() {
        binding.signOutBtn.setOnClickListener{
//            FirebaseAuth.getInstance().signOut()  //If I need Every sign out Pop me Google mail so this below code.
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
            GoogleSignIn.getClient(requireActivity(),gso).signOut()
            replaceFragment(LogInFragment())
//            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main,LogInFragment()).commit()
        }
    }

}