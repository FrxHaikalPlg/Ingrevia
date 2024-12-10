package com.frxhaikal_plg.ingrevia.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.frxhaikal_plg.ingrevia.R
import com.frxhaikal_plg.ingrevia.databinding.ActivityRegisterBinding
import com.frxhaikal_plg.ingrevia.data.local.UserPreferences
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        userPreferences = UserPreferences(this)
        
        setupButtons()
    }
    
    private fun setupButtons() {
        binding.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.registerButton.setOnClickListener {
            if (!isInputValid()) {
                Toast.makeText(this, getString(R.string.please_complete_register), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val password = binding.edRegisterPassword.text.toString()
            val confirmPassword = binding.edRegisterConfirmPassword.text.toString()
            
            if (password != confirmPassword) {
                binding.edRegisterConfirmPassword.error = getString(R.string.password_not_match)
                return@setOnClickListener
            } else {
                binding.edRegisterConfirmPassword.error = null
            }
            
            handleRegister()
        }
        
        binding.loginHere.setOnClickListener {
            finish()
        }
    }
    
    private fun isInputValid(): Boolean {
        return binding.edRegisterEmail.error == null && 
               binding.edRegisterPassword.error == null &&
               binding.edRegisterConfirmPassword.error == null &&
               binding.edRegisterEmail.text?.isNotEmpty() == true &&
               binding.edRegisterPassword.text?.isNotEmpty() == true &&
               binding.edRegisterConfirmPassword.text?.isNotEmpty() == true
    }
    
    private fun handleRegister() {
        setLoading(true)
        lifecycleScope.launch {
            try {
                // TODO: Implementasi register ke API
                startActivity(Intent(this@RegisterActivity, UserInformationActivity::class.java))
                finishAffinity()
            } catch (e: Exception) {
                Toast.makeText(
                    this@RegisterActivity, 
                    getString(R.string.register_failed, e.message), 
                    Toast.LENGTH_SHORT
                ).show()
            } finally {
                setLoading(false)
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.loading.loadingOverlay.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading) {
            binding.loading.loadingText.text = getString(R.string.registering)
        }
        binding.registerButton.isEnabled = !isLoading
        binding.edRegisterEmail.isEnabled = !isLoading
        binding.edRegisterPassword.isEnabled = !isLoading
        binding.edRegisterConfirmPassword.isEnabled = !isLoading
    }
}