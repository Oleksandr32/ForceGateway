package com.oleksandrlysun.forcegateway.presentation.screens.filespicker.di

import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.extensions.findFragmentByType
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import com.oleksandrlysun.forcegateway.presentation.screens.filespicker.*
import dagger.Module
import dagger.Provides

@Module
class FilesPickerModule(private val activity: FilesPickerActivity) {

	@Provides
	@ActivityScope
	fun provideRouter(): FilesPickerRouter {
		return FilesPickerRouter(activity)
	}

	@Provides
	@ActivityScope
	fun provideView(): FilesPickerView? {
		return activity.findFragmentByType<FilesPickerView>()
	}

	@Provides
	@ActivityScope
	fun provideStoragePermissionsDelegate(): StoragePermissionsDelegate {
		return activity
	}

	@Provides
	@ActivityScope
	fun provideFilesPickerAdapter(presenter: FilesPickerPresenter): FilesPickerAdapter {
		return FilesPickerAdapter(presenter)
	}
}