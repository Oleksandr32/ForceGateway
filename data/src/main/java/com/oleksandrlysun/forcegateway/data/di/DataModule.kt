package com.oleksandrlysun.forcegateway.data.di

import android.content.Context
import com.oleksandrlysun.forcegateway.data.storage.StorageServiceImpl
import com.oleksandrlysun.forcegateway.domain.boundaries.StorageService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideStorageService(): StorageService = StorageServiceImpl()
}