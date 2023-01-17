package com.example.netflix.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.netflix.Activity.MainActivity
import com.example.netflix.R
import com.example.netflix.databinding.FragmentLoginBinding
import com.example.netflix.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignupFragment : Fragment(R.layout.fragment_signup) {

    private lateinit var binding: FragmentSignupBinding

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = Firebase.auth

        binding = FragmentSignupBinding.bind(view)

        binding.BtnSignIn.setOnClickListener {


            val animator1 = ObjectAnimator.ofFloat( binding.BtnSignIn, "scaleX", 0.9f, 1.1f)
            val animator2 = ObjectAnimator.ofFloat( binding.BtnSignIn, "scaleY", 0.9f, 1.1f)

            val set = AnimatorSet()
            set.playTogether(animator1, animator2)
            set.duration = 500
            set.start()


            var name = binding.email.text.toString()
            var password = binding.password2.text.toString()
            var confirm = binding.confirmPassword.text.toString()

            var data = true

            if (name.equals("")) {
                binding.email.error = "invalid"
                data = false
            }
            if (password.equals("")) {
                binding.password2.error = "invalid"
                data = false
            }

            if (password.length <5) {
                binding.password2.error = "very short"
                data = false
            }

            if (password != confirm) {
                binding.confirmPassword.error = "invalid"
                data = false
            }
            if (!isValidEmail(name)) {
                data = false
                binding.email.error = "invalid"
            }

            if (data) {
                createAccount(name, password)
            }

        }
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()

                }
            }
    }
}