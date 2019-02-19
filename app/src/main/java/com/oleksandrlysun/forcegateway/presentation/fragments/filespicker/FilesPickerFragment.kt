package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrlysun.forcegateway.ForceGatewayApplication.Companion.applicationComponent
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.extensions.bindView
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.di.FilesPickerModule
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import java.io.File
import javax.inject.Inject

class FilesPickerFragment : Fragment(), FilesPickerView, StoragePermissionsDelegate {

	@Inject
	lateinit var presenter: FilesPickerPresenter

	@Inject
	lateinit var filesPickerAdapter: FilesPickerAdapter

	@Inject
	lateinit var filesPickerManager: RecyclerView.LayoutManager

	var output: FilesPickerOutput? = null

	private val mFilesPickerRecyclerView by bindView<RecyclerView>(R.id.recycler_view_files_picker)

	override fun onAttach(context: Context?) {
		super.onAttach(context)
		output = context as? FilesPickerOutput
		if (output == null) {
			throw ClassCastException("$context must implement FilesPickerOutput")
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		injectDependencies()
		return inflater.inflate(R.layout.fragment_files_picker, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupRecyclerView()
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
			if (grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED) {
				presenter.onStoragePermissionsGranted()
			} else {
				output?.onStoragePermissionsDenied()
			}
		}
	}

	override fun setFiles(files: List<File>) {
		filesPickerAdapter.items = files
	}

	override fun hasStoragePermissions(): Boolean {
		val context = this.context ?: return false
		return STORAGE_PERMISSIONS.all { permission ->
			ContextCompat.checkSelfPermission(context, permission) == PERMISSION_GRANTED
		}
	}

	override fun requestStoragePermissions() {
		requestPermissions(STORAGE_PERMISSIONS, STORAGE_PERMISSION_REQUEST_CODE)
	}

	private fun setupRecyclerView() {
		mFilesPickerRecyclerView.run {
			setHasFixedSize(true)
			layoutManager = filesPickerManager
			adapter = filesPickerAdapter
			addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
		}
	}

	private fun injectDependencies() {
		val module = FilesPickerModule(this)
		applicationComponent.getFilesPickerComponent(module).inject(this)
	}

	companion object {

		private const val STORAGE_PERMISSION_REQUEST_CODE = 0
		private val STORAGE_PERMISSIONS = arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
	}
}