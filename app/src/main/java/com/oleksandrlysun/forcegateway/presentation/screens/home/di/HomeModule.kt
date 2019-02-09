package com.oleksandrlysun.forcegateway.presentation.screens.home.di

import androidx.fragment.app.Fragment
import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.presentation.screens.home.HomeActivity
import com.oleksandrlysun.forcegateway.presentation.screens.home.HomeFragmentPagerAdapter
import dagger.Module
import dagger.Provides

@Module
class HomeModule(private val activity: HomeActivity) {

    @Provides
    @ActivityScope
    fun provideHomeFragmentPagerAdapter(): HomeFragmentPagerAdapter {
        val fragments = listOf<Fragment>()
        return HomeFragmentPagerAdapter(activity.supportFragmentManager, fragments)
    }
}