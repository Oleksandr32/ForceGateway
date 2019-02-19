package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.di

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrlysun.forcegateway.di.FragmentScope
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilesPickerAdapter
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilesPickerFragment
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilesPickerView
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import dagger.Module
import dagger.Provides

@Module
class FilesPickerModule(private val fragment: FilesPickerFragment) {

	@Provides
	@FragmentScope
	fun provideView(): FilesPickerView = fragment

	@Provides
	@FragmentScope
	fun provideStoragePermissionsDelegate(): StoragePermissionsDelegate = fragment

	@Provides
	@FragmentScope
	fun provideFilesPickerAdapter() = FilesPickerAdapter()

	@Provides
	@FragmentScope
	fun provideFilesPickerManager(): RecyclerView.LayoutManager {
		return LinearLayoutManager(fragment.context)
	}
}