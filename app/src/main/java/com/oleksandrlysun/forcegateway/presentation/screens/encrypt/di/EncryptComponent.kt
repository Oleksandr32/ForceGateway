package com.oleksandrlysun.forcegateway.presentation.screens.encrypt.di

import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.EncryptActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [EncryptModule::class])
interface EncryptComponent {

	fun inject(activity: EncryptActivity)
}