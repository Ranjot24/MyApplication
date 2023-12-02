// AuthManager.kt

import com.example.myapplication.AuthService
import com.example.myapplication.LoginRequest
import com.example.myapplication.LoginResponse
import com.example.myapplication.SignUpRequest
import com.example.myapplication.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthManager {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://your-backend-url.com/") // Replace with your backend URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val AuthService = retrofit.create(AuthService::class.java)

    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        val call = AuthService.login(LoginRequest(email, password))

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // Authentication successful
                    callback(true)
                } else {
                    // Authentication failed
                    callback(false)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Network or server error
                callback(false)
            }
        })
    }

    // AuthManager.kt

// ... (existing code)

    fun logout(callback: (Boolean) -> Unit) {
        val call = AuthService.logout()

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Logout successful
                    callback(true)
                } else {
                    // Logout failed
                    callback(false)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Network or server error
                callback(false)
            }
        })
    }

    fun signUp(email: String, password: String, callback: (Boolean) -> Unit) {
        val call = AuthService.signUp(SignUpRequest(email, password))

        call.enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if (response.isSuccessful) {
                    // Sign-up successful
                    callback(true)
                } else {
                    // Sign-up failed
                    callback(false)
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                // Network or server error
                callback(false)
            }
        })
    }
}


