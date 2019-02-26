package com.oleksandrlysun.forcegateway.presentation.screens.filespicker

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.oleksandrlysun.forcegateway.ForceGatewayApplication.Companion.applicationComponent
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.domain.models.FileModel
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import com.oleksandrlysun.forcegateway.presentation.screens.filespicker.di.FilesPickerComponent
import com.oleksandrlysun.forcegateway.presentation.screens.filespicker.di.FilesPickerModule
import javax.inject.Inject

class FilesPickerActivity : AppCompatActivity(), StoragePermissionsDelegate {

	@Inject
	lateinit var presenter: FilesPickerPresenter

	lateinit var component: FilesPickerComponent

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		createComponent()
		setContentView(R.layout.activity_files_picker)
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
			if (grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED) {
				presenter.onStoragePermissionsGranted()
			} else {

			}
		}
	}

	override fun hasStoragePermissions(): Boolean {
		return STORAGE_PERMISSIONS.all { permission ->
			ContextCompat.checkSelfPermission(this, permission) == PERMISSION_GRANTED
		}
	}

	override fun requestStoragePermissions() {
		ActivityCompat.requestPermissions(this, STORAGE_PERMISSIONS, STORAGE_PERMISSION_REQUEST_CODE)
	}

	private fun createComponent() {
		val module = FilesPickerModule(this)
		component = applicationComponent.getFilesPickerComponent(module)
		component.inject(this)
	}

	companion object {
		private const val STORAGE_PERMISSION_REQUEST_CODE = 0
		private val STORAGE_PERMISSIONS = arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
	}
}