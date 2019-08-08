package com.example.hcmbtsid.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*

import com.example.hcmbtsid.R
import com.example.hcmbtsid.data.model.Karyawan
import com.example.hcmbtsid.repository.DataRepository
import com.example.hcmbtsid.ui.forgotPassword.ForgotPassword
import com.example.hcmbtsid.ui.menu.Menumain_Activity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        // set id for every id
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)


        // change activity
        val forgotPassword_tv = findViewById<TextView>(R.id.forgotpassword)
        forgotPassword_tv.setOnClickListener(){
            val i = Intent(this, ForgotPassword::class.java)
            i.putExtra("MANTAP" , 1)
            callForgot(i)
        }





        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loginUser(username.text.trim().toString() , password.text.trim().toString())
//                callA()
//                loginViewModel.login(username.text.toString(), password.text.toString())

            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    fun startAnotherActivity(pc: Context,cls: Activity ){
        startActivity(Intent(this , cls::class.java))

    }
    fun startMenu(karyawan:Karyawan){
        val intent = Intent(this, Menumain_Activity::class.java)
        intent.putExtra("Karyawan" , karyawan.nip)
        startActivity(intent)

    }

    fun callForgot(intent: Intent){
        startActivity(intent)
    }
    fun loginUser(email:String , password: String){
        val userServices = DataRepository.create()
        userServices.loginKaryawan(email,password).enqueue(object : Callback<Karyawan> {
            override fun onResponse(call: Call<Karyawan>, response: Response<Karyawan>) {
//                Toast.makeText(applicationContext , "NIP baru : ${response.body()?.toString()}", Toast.LENGTH_LONG).show()
                startMenu(response.body()!!)
            }

            override fun onFailure(call: Call<Karyawan>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })



}



