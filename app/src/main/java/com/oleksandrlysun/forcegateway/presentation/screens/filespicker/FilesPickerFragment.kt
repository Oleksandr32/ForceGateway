package com.oleksandrlysun.forcegateway.presentation.screens.filespicker

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.domain.models.FileModel
import com.oleksandrlysun.forcegateway.extensions.bindView
import javax.inject.Inject

class FilesPickerFragment : Fragment(), FilesPickerView {

	@Inject
	lateinit var presenter: FilesPickerPresenter

	@Inject
	lateinit var filesPickerAdapter: FilesPickerAdapter

	@Inject
	lateinit var filesPickerManager: RecyclerView.LayoutManager

	var listener: FilesPickerListener? = null

	private val mFilesPickerRecyclerView by bindView<RecyclerView>(R.id.recycler_view_files_picker)
	private val mEmptyFolderContainer by bindView<LinearLayout>(R.id.container_empty_folder)

	override fun onAttach(context: Context?) {
		super.onAttach(context)
		listener = context as? FilesPickerListener
		if (listener == null) {
			throw ClassCastException("$context must implement FilesPickerListener")
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		injectDependencies()
		return inflater.inflate(R.layout.fragment_files_picker, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		handleArguments()
		setupRecyclerView()
	}

	override fun setFiles(files: List<FileModel>) {
		filesPickerAdapter.setItems(files)
	}

	override fun setFilesPickerState(state: FilePickerState) {
		when (state) {
			FilePickerState.FETCHED -> {
				mFilesPickerRecyclerView.visibility = View.VISIBLE
				mEmptyFolderContainer.visibility = View.GONE
			}
			FilePickerState.EMPTY -> {
				mFilesPickerRecyclerView.visibility = View.GONE
				mEmptyFolderContainer.visibility = View.VISIBLE
			}
		}
	}

	private fun handleArguments() {
		arguments?.let { bundle ->
			val path = bundle.getString(ARG_PATH)
			presenter.onPathChanged(path)
		}
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
		(activity as FilesPickerActivity).component.inject(this)
	}

	companion object {

		private const val ARG_PATH = "filespicker.path"

		@JvmStatic
		fun newInstance(path: String?): FilesPickerFragment {
			return FilesPickerFragment().apply {
				arguments = Bundle().apply {
					putString(ARG_PATH, path)
				}
			}
		}
	}
}