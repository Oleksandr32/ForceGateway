package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.di

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.oleksandrlysun.forcegateway.di.FragmentScope
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilesPickerAdapter
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilesPickerFragment
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilesPickerPresenter
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilesPickerView
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import dagger.Module
import dagger.Provides

@Module
class FilesPickerModule(private val fragment: FilesPickerFragment) {

	@Provides
	@FragmentScope
	fun provideView(): FilesPickerView {
		return fragment
	}

	@Provides
	@FragmentScope
	fun provideStoragePermissionsDelegate(): StoragePermissionsDelegate {
		return fragment
	}

	@Provides
	@FragmentScope
	fun provideAdapter(presenter: FilesPickerPresenter): FilesPickerAdapter {
		return FilesPickerAdapter(presenter)
	}

	@Provides
	@FragmentScope
	fun provideLayoutManager(): LayoutManager {
		return LinearLayoutManager(fragment.context)
	}
}