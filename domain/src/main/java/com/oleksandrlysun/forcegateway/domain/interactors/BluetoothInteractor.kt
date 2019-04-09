package com.oleksandrlysun.forcegateway.domain.interactors

import com.oleksandrlysun.forcegateway.domain.boundaries.BluetoothService
import com.oleksandrlysun.forcegateway.domain.models.BluetoothDevice
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BluetoothInteractor @Inject constructor(private val bluetoothService: BluetoothService) {

	fun discoverDevices(): Observable<BluetoothDevice> {
		return bluetoothService.discoverDevices()
				.subscribeOn(Schedulers.io())
	}
}