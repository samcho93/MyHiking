package com.myhiking.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.myhiking.app.databinding.ActivityMainBinding
import com.myhiking.app.ui.map.MapActivity
import com.myhiking.app.util.PermissionHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted || PermissionHelper.hasPartialAccess(this)) {
            launchMapActivity()
        } else {
            showPermissionUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPermission.setOnClickListener {
            requestPermissions()
        }

        if (PermissionHelper.hasPhotoPermission(this)) {
            launchMapActivity()
        } else {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        binding.progressBar.visibility = View.VISIBLE
        binding.tvStatus.visibility = View.VISIBLE
        binding.tvStatus.text = getString(R.string.loading_photos)
        binding.btnPermission.visibility = View.GONE

        permissionLauncher.launch(PermissionHelper.getRequiredPermissions())
    }

    private fun showPermissionUI() {
        binding.progressBar.visibility = View.GONE
        binding.tvStatus.visibility = View.VISIBLE
        binding.tvStatus.text = getString(R.string.permission_rationale)
        binding.btnPermission.visibility = View.VISIBLE
    }

    private fun launchMapActivity() {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
        finish()
    }
}
