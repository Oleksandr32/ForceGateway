package com.oleksandrlysun.forcegateway.presentation.screens.filespicker.di

import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.presentation.screens.filespicker.FilesPickerFragment
import com.oleksandrlysun.forcegateway.presentation.screens.filespicker.FilesPickerActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [FilesPickerModule::class])
interface FilesPickerComponent {

	fun inject(activity: FilesPickerActivity)

	fun inject(fragment: FilesPickerFragment)
}