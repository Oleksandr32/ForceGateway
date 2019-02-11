package com.oleksandrlysun.forcegateway.presentation.screens.home.di

import com.oleksandrlysun.forcegateway.di.ActivityScope
import com.oleksandrlysun.forcegateway.presentation.screens.home.FeaturesOverviewFragment
import com.oleksandrlysun.forcegateway.presentation.screens.home.HomeActivity
import com.oleksandrlysun.forcegateway.presentation.screens.home.HomeTabsFragmentPagerAdapter
import dagger.Module
import dagger.Provides

@Module
class HomeModule(private val activity: HomeActivity) {

    @Provides
    @ActivityScope
    fun provideHomeTabsFragmentPagerAdapter(): HomeTabsFragmentPagerAdapter {
        return HomeTabsFragmentPagerAdapter(activity.supportFragmentManager).apply {
            fragments.add(FeaturesOverviewFragment())
            fragments.add(FeaturesOverviewFragment())
        }
    }
}