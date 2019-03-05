package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.di

import com.oleksandrlysun.forcegateway.di.FragmentScope
import com.oleksandrlysun.forcegateway.presentation.fragments.filespicker.FilesPickerFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FilesPickerModule::class])
interface FilesPickerComponent {

	fun inject(fragment: FilesPickerFragment)
}