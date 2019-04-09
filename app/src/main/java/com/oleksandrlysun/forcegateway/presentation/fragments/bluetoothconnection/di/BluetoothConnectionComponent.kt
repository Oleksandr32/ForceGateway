package com.oleksandrlysun.forcegateway.presentation.fragments.bluetoothconnection.di

import com.oleksandrlysun.forcegateway.di.FragmentScope
import com.oleksandrlysun.forcegateway.presentation.fragments.bluetoothconnection.BluetoothConnectionFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [BluetoothConnectionModule::class])
interface BluetoothConnectionComponent {

	fun inject(fragment: BluetoothConnectionFragment)
}