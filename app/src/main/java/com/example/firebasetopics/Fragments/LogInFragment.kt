package com.example.firebasetopics.Fragments
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.firebasetopics.R
import com.example.firebasetopics.databinding.FragmentLogInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.GoogleAuthProvider


class LogInFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding:FragmentLogInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient:GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_log_in, container, false)
        binding = FragmentLogInBinding.inflate(inflater,container,false)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        googleSignInClient=GoogleSignIn.getClient(requireActivity(),gso)
        onClickListener()
        return binding.root
    }
/*//Check if the user Logged in.
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null){
//            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main,HomeFragment())
//                .commit()
            replaceFragment(HomeFragment())
        } else{
            Toast.makeText(requireContext(),"You are Not Registered",Toast.LENGTH_LONG).show()
        }
    }*/
    private fun onClickListener(){
        binding.loginInBtn.setOnClickListener(this)
        binding.signUpBtn.setOnClickListener(this)
        binding.googleBtn.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.loginInBtn->{
                val emailAddress = binding.emailSignUp.text.toString()
                val password = binding.passwordSignIn.text.toString()

                if (emailAddress.isEmpty() || password.isEmpty()){
                    Toast.makeText(requireContext(),"Please Fill All The Details",Toast.LENGTH_LONG).show()
                } else{
                    auth.signInWithEmailAndPassword(emailAddress,password)
                        .addOnCompleteListener{task ->
                            if (task.isSuccessful){
                                Toast.makeText(requireContext(),"Sign-In Successful",Toast.LENGTH_LONG).show()
//                                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main,HomeFragment())
//                                    .addToBackStack(null).commit()
                                replaceFragment(HomeFragment())
                            } else{
                                Toast.makeText(requireContext(),"Sign-In Failed:${task.exception?.message}",Toast.LENGTH_LONG).show()
                            }
                        }
                }
            }
            R.id.signUpBtn->{
                replaceFragment(SignUpFragment())
//                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main,SignUpFragment())
//                    .commit()

            }
            R.id.googleBtn->{
                val signInClient = googleSignInClient.signInIntent
                launcher.launch(signInClient)
            }
        }


    }
    private val launcher=registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        result->
        if (result.resultCode== Activity.RESULT_OK)
        {
               val task=GoogleSignIn.getSignedInAccountFromIntent(result.data)
            if (task.isSuccessful){
                val account:GoogleSignInAccount?=task.result
                val credential=GoogleAuthProvider.getCredential(account?.idToken,null)
                auth.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful)
                    {
//                        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main,HomeFragment()).commit()
                        replaceFragment(HomeFragment())
                    }
                    else{
                        Toast.makeText(requireContext(),"Failed",Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else{
            Toast.makeText(requireContext(),"Failed",Toast.LENGTH_LONG).show()
        }
    }

}