package com.example.curse

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.petersamokhin.vksdk.android.auth.VkAuth
import com.petersamokhin.vksdk.android.auth.model.VkAuthResult
import com.vk.api.sdk.VK

class MainActivity : AppCompatActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auth_button: Button = findViewById(R.id.auth_button)

        auth_button.setOnClickListener {
            val appId = 8008208
            val scopes = listOf(VkAuth.Scope.Offline, VkAuth.Scope.Email)
            var id = 0

            VkAuth.loginWithWebView(this, appId, VkAuth.ResponseType.AccessToken, scopes) { result ->
                when (result) {
                    is VkAuthResult.AccessToken -> {
                        // do something with result.accessToken
                        id = result.userId

                        val intent = Intent(this@MainActivity, ListActivity::class.java)
                        intent.putExtra("id", id.toString())
                        startActivity(intent)
                    }
                    is VkAuthResult.Error -> {
                        // do something with result.error
                        print(result.error)
                        print(result.reason)
                        print(result.description)
                    }
                }
            }
        }
    }
}