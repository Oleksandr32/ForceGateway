package com.oleksandrlysun.forcegateway.presentation.fragments.bluetoothconnection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oleksandrlysun.forcegateway.ForceGatewayApplication.Companion.applicationComponent
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.presentation.fragments.bluetoothconnection.di.BluetoothConnectionModule
import javax.inject.Inject

class BluetoothConnectionFragment : Fragment(), BluetoothConnectionView {

	@Inject
	lateinit var presenter: BleConnectionPresenter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		injectDependencies()
		return inflater.inflate(R.layout.fragment_bluetooth_connection, container, false)
	}

	private fun injectDependencies() {
		val module = BluetoothConnectionModule(this)
		val component = applicationComponent.getBluetoothConnectionComponent(module)
		component.inject(this)
	}
}