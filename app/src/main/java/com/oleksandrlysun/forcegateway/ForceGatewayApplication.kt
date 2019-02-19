package com.oleksandrlysun.forcegateway

import android.app.Application
import com.oleksandrlysun.forcegateway.data.di.DataModule
import com.oleksandrlysun.forcegateway.di.ApplicationComponent
import com.oleksandrlysun.forcegateway.di.DaggerApplicationComponent
import com.oleksandrlysun.forcegateway.domain.di.DomainModule

class ForceGatewayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .dataModule(DataModule(this))
                .domainModule(DomainModule())
                .build()
    }

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }
}