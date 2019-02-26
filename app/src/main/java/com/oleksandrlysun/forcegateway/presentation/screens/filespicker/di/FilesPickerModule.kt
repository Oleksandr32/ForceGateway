package com.oleksandrlysun.forcegateway.presentation.screens.filespicker.di

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.extensions.findFragmentByType
import com.oleksandrlysun.forcegateway.presentation.permissions.StoragePermissionsDelegate
import com.oleksandrlysun.forcegateway.presentation.screens.filespicker.FilesPickerActivity
import com.oleksandrlysun.forcegateway.presentation.screens.filespicker.FilesPickerAdapter
import com.oleksandrlysun.forcegateway.presentation.screens.filespicker.FilesPickerView
import com.oleksandrlysun.forcegateway.presentation.screens.filespicker.FilesPickerRouter
import dagger.Module
import dagger.Provides

@Module
class FilesPickerModule(private val activity: FilesPickerActivity) {

    @Provides
    @ActivityScope
    fun provideRouter() = FilesPickerRouter(activity)

    @Provides
    @ActivityScope
    fun provideView() = activity.findFragmentByType<FilesPickerView>()

    @Provides
    @ActivityScope
    fun provideStoragePermissionsDelegate(): StoragePermissionsDelegate = activity

    @Provides
    @ActivityScope
    fun provideFilesPickerAdapter() = FilesPickerAdapter()

    @Provides
    @ActivityScope
    fun provideFilesPickerManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity)
    }

}