package com.oleksandrlysun.forcegateway.di

import com.oleksandrlysun.forcegateway.data.di.DataModule
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.di.EncryptComponent
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.di.EncryptModule
import com.oleksandrlysun.forcegateway.presentation.screens.home.di.HomeComponent
import com.oleksandrlysun.forcegateway.presentation.screens.home.di.HomeModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface ApplicationComponent {

    fun getHomeComponent(module: HomeModule): HomeComponent

    fun getEncryptComponent(module: EncryptModule): EncryptComponent
}