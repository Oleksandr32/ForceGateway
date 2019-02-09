package com.oleksandrlysun.forcegateway.di

import com.oleksandrlysun.forcegateway.presentation.screens.home.di.HomeComponent
import com.oleksandrlysun.forcegateway.presentation.screens.home.di.HomeModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent {

    fun getHomeComponent(module: HomeModule): HomeComponent
}