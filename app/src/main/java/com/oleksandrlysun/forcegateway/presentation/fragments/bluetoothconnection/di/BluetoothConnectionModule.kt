package com.oleksandrlysun.forcegateway.presentation.fragments.bluetoothconnection.di

import com.oleksandrlysun.forcegateway.di.FragmentScope
import com.oleksandrlysun.forcegateway.presentation.fragments.bluetoothconnection.BluetoothConnectionFragment
import com.oleksandrlysun.forcegateway.presentation.fragments.bluetoothconnection.BluetoothConnectionView
import dagger.Module
import dagger.Provides

@Module
class BluetoothConnectionModule(private val fragment: BluetoothConnectionFragment) {

	@Provides
	@FragmentScope
	fun provideView(): BluetoothConnectionView {
		return fragment
	}
}