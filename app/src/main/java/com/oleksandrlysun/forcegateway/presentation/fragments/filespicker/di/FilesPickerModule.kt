package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.di

import com.oleksandrlysun.forcegateway.di.FragmentScope
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
}