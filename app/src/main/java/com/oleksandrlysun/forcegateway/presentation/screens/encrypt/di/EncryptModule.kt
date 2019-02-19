package com.oleksandrlysun.forcegateway.presentation.screens.encrypt.di

import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.EncryptActivity
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.EncryptRouter
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.EncryptView
import dagger.Module
import dagger.Provides

@Module
class EncryptModule(private val activity: EncryptActivity) {

	@Provides
	@ActivityScope
	fun provideView(): EncryptView = activity

	@Provides
	@ActivityScope
	fun provideRouter() = EncryptRouter(activity)
}