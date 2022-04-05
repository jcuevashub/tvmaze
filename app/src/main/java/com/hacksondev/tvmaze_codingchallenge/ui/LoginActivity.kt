package com.hacksondev.tvmaze_codingchallenge.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import com.hacksondev.tvmaze_codingchallenge.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkBioMetricSupported()
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this@LoginActivity,
            executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    ContextCompat.startActivity(this@LoginActivity, intent, null)
                    Toast.makeText(
                        applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    //attempt not regconized fingerprint
                    Toast.makeText(
                        applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        binding.btnFp.setOnClickListener {
            val promptInfo: PromptInfo.Builder = dialogMetric()
            promptInfo.setNegativeButtonText("Cancel")
            biometricPrompt.authenticate(promptInfo.build())
        }

        binding.btnFppin.setOnClickListener {
            val promptInfo: PromptInfo.Builder = dialogMetric()
            promptInfo.setDeviceCredentialAllowed(true)
            biometricPrompt.authenticate(promptInfo.build())
        }
    }

    private fun dialogMetric(): PromptInfo.Builder {
        //Show prompt dialog
        return PromptInfo.Builder()
            .setTitle("Biometric login")
            .setSubtitle("Log in using your biometric credential")
    }

    private fun checkBioMetricSupported() {
        val manager = BiometricManager.from(this)
        var info = ""
        when (manager.canAuthenticate(Authenticators.BIOMETRIC_WEAK or Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                info = "App can authenticate using biometrics."
                enableButton(true)
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                info = "No biometric features available on this device."
                enableButton(false)
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                info = "Biometric features are currently unavailable."
                enableButton(false)
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                info = "Need register at least one finger print"
                enableButton(false, true)
            }
            else -> {
                info = "Unknown cause"
                enableButton(false)
            }
        }
        binding.txInfo.text = info
    }

    private fun enableButton(enable: Boolean) {
        binding.btnFp.isEnabled = enable
        binding.btnFppin.isEnabled = true
    }

    private fun enableButton(enable: Boolean, enroll: Boolean) {
        enableButton(enable)
        if (!enroll) return

    }
}