package com.example.netflix.Activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.netflix.R
import com.example.netflix.fragments.LoginFragment
import com.example.netflix.fragments.SignupFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth




    lateinit var loginButton: Button
    lateinit var signinButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.commit {
            replace<LoginFragment>(R.id.fragment_container_view)
            setReorderingAllowed(true)
            addToBackStack(null)

        }

        auth = Firebase.auth

        loginButton = findViewById(R.id.logInButton)
        signinButton = findViewById(R.id.signInButton)

        loginButton.setOnClickListener {

        }
        signinButton.setOnClickListener {

        }

        loginButton.setOnClickListener {


            val animator1 = ObjectAnimator.ofFloat(loginButton, "scaleX", 0.9f, 1.1f)
            val animator2 = ObjectAnimator.ofFloat(loginButton, "scaleY", 0.9f, 1.1f)

            val set = AnimatorSet()
            set.playTogether(animator1, animator2)
            set.duration = 500
            set.start()
            signinButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.red))
            loginButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black))
            supportFragmentManager.commit {
                replace<LoginFragment>(R.id.fragment_container_view)
                setReorderingAllowed(true)
            }
            var data = true
//            if (username.text.toString().isEmpty()) {
//                username.setError("invalid name")
//                data = false
//            }
//            if (password.text.toString().isEmpty()) {
//                password.setError("invalid password")
//                data = false
//            }
//            if (password.text.toString().length < 5) {
//                password.setError("very short")
//                data = false
//            }
//
//            if (data) {
//                signIn(username.text.toString(), password.text.toString())
//            }

        }
        signinButton.setOnClickListener {

            val animator1 = ObjectAnimator.ofFloat(signinButton, "scaleX", 0.9f, 1f)
            val animator2 = ObjectAnimator.ofFloat(signinButton, "scaleY", 0.9f, 1f)

            val set = AnimatorSet()
            set.playTogether(animator1, animator2)
            set.duration = 500
            set.start()
            signinButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black))
            loginButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.red))


            supportFragmentManager.commit {
                replace<SignupFragment>(R.id.fragment_container_view)
                setReorderingAllowed(true)
            }

//            var data = true
//            if (username2.text.toString().isEmpty()) {
//                username2.setError("invalid name")
//                data = false
//            }
//            if (password2.text.toString().isEmpty()) {
//                password2.setError("invalid password")
//                data = false
//            }
//            if (password2.text.toString().length < 5) {
//                password2.setError("very short")
//                data = false
//            }
//
//            if (!confirm.text.toString().equals(password2.text.toString())) {
//                confirm.setError("not Matching")
//                data = false
//            }
//            if (data) {
//                createAccount(username2.text.toString(), password2.text.toString())
//            }

        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {

        startActivity(Intent(this@LoginActivity, MainActivity::class.java).putExtra("splashScreen", "splashScreen"))
        finish()

        FirebaseDatabase.getInstance().reference.child("Users").push().setValue("")



        FirebaseDatabase.getInstance().reference.child("UserData").push().setValue("")

    }

    private fun reload() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java).putExtra("splashScreen", "splashScreen"))
        finish()
    }
}
