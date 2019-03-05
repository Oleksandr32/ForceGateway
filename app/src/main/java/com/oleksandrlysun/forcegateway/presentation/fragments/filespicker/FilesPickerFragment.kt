package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.oleksandrlysun.forcegateway.ForceGatewayApplication.Companion.applicationComponent
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.domain.models.FileModel
import com.oleksandrlysun.forcegateway.extensions.bindView
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.di.FilesPickerModule
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import javax.inject.Inject

class FilesPickerFragment : Fragment(), FilesPickerView, StoragePermissionsDelegate {

	@Inject
	lateinit var presenter: FilesPickerPresenter

	@Inject
	lateinit var adapter: FilesPickerAdapter

	@Inject
	lateinit var layoutManager: LayoutManager

	private var output: FilesPickerOutput? = null

	private val pickerRecyclerView by bindView<RecyclerView>(R.id.recycler_view_files_picker)
	private val emptyFolderContainer by bindView<LinearLayout>(R.id.container_empty_folder)

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
				output?.onStoragePermissionDenied()
			}
		}
	}

	override fun setFiles(files: List<FileModel>) {
		adapter.setItems(files)
	}

	override fun setFilesPickerState(state: FilePickerState) {
		when (state) {
			FilePickerState.FETCHED -> {
				pickerRecyclerView?.visibility = View.VISIBLE
				emptyFolderContainer?.visibility = View.GONE
			}
			FilePickerState.EMPTY -> {
				pickerRecyclerView?.visibility = View.GONE
				emptyFolderContainer?.visibility = View.VISIBLE
			}
		}
	}

	override fun hasStoragePermissions(): Boolean {
		return STORAGE_PERMISSIONS.all { permission ->
			ContextCompat.checkSelfPermission(requireContext(), permission) == PERMISSION_GRANTED
		}
	}

	override fun requestStoragePermissions() {
		requestPermissions(STORAGE_PERMISSIONS, STORAGE_PERMISSION_REQUEST_CODE)
	}

	fun setOutput(output: FilesPickerOutput) {
		this.output = output
	}

	private fun injectDependencies() {
		val module = FilesPickerModule(this)
		val component = applicationComponent.getFilesPickerComponent(module)
		component.inject(this)
	}

	private fun setupRecyclerView() {
		pickerRecyclerView?.layoutManager = layoutManager
		pickerRecyclerView?.adapter = adapter
		pickerRecyclerView?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
	}

	companion object {

		private const val STORAGE_PERMISSION_REQUEST_CODE = 0
		private val STORAGE_PERMISSIONS = arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
	}
}