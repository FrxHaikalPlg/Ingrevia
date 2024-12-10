package com.frxhaikal_plg.ingrevia.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.frxhaikal_plg.ingrevia.MainActivity
import com.frxhaikal_plg.ingrevia.R
import com.frxhaikal_plg.ingrevia.databinding.ActivityUserInformationBinding
import kotlinx.coroutines.launch

class UserInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserInformationBinding
    private var selectedActivityLevel: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupButtons()
        setupRadioGroup()
    }
    
    private fun setupRadioGroup() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            selectedActivityLevel = when (checkedId) {
                R.id.radio_basic -> 1
                R.id.radio_simple -> 2
                R.id.radio_standard -> 3
                R.id.radio_advanced -> 4
                R.id.radio_comprehensive -> 5
                else -> 0
            }
        }
    }
    
    private fun setupButtons() {
        binding.backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.startedButton.setOnClickListener {
            if (isInputValid()) {
                handleSaveInformation()
            } else {
                Toast.makeText(this, getString(R.string.please_complete_data), Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun isInputValid(): Boolean {
        return binding.edHeight.error == null && 
               binding.edWeight.error == null &&
               binding.edHeight.text?.isNotEmpty() == true &&
               binding.edWeight.text?.isNotEmpty() == true &&
               selectedActivityLevel != 0
    }
    
    private fun handleSaveInformation() {
        lifecycleScope.launch {
            try {
                val userInfo = mapOf(
                    "height" to binding.edHeight.text.toString().toInt(),
                    "weight" to binding.edWeight.text.toString().toInt(),
                    "activity_level" to selectedActivityLevel
                )
                
                startActivity(Intent(this@UserInformationActivity, MainActivity::class.java))
                finishAffinity()
            } catch (e: Exception) {
                Toast.makeText(this@UserInformationActivity, getString(R.string.failed_save_data, e.message), Toast.LENGTH_SHORT).show()
            }
        }
    }
}