package com.example.firebasetopics.Fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.firebasetopics.R
import com.example.firebasetopics.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        auth = FirebaseAuth.getInstance()
        onClickListener()
        return binding.root

    }

    private fun onClickListener() {
        binding.signInBtn.setOnClickListener(this)
        binding.registerBtn.setOnClickListener(this)
    }

    override fun onClick(v:View?){
        when(v!!.id){
            R.id.signInBtn->{
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main,LogInFragment())
                    .addToBackStack(null).commit()
            }
            R.id.registerBtn->{
                //get text from edit text field
                val email = binding.emailSignUp.text.toString()
                val username = binding.usernameSignUp.text.toString()
                val password = binding.passwordSignUp.text.toString()
                val repeatPassword = binding.repeatPassSignUp.text.toString()

                //check if any field is Empty.
                if (email.isEmpty() || username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()){
                    Toast.makeText(requireContext(),"Please Fill All The Details",Toast.LENGTH_LONG).show()
                } else if (password != repeatPassword){
                    Toast.makeText(requireContext(),"Repeat Password must be Same",Toast.LENGTH_LONG).show()
                } else{
                    auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(Activity()){ task ->
                            if (task.isSuccessful){
                                Toast.makeText(requireContext(),"Registration Successful",Toast.LENGTH_LONG).show()
                                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main,LogInFragment())
                                    .addToBackStack(null).commit()
                            } else{
                                Toast.makeText(requireContext(),"Registration Failed:${task.exception?.message}",Toast.LENGTH_LONG).show()
                            }

                        }
                }
            }
            }


            }
}