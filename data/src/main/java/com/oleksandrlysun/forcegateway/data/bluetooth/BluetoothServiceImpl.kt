package com.oleksandrlysun.forcegateway.data.bluetooth

import android.content.Context
import com.github.ivbaranov.rxbluetooth.RxBluetooth
import com.oleksandrlysun.forcegateway.data.mappers.BluetoothDeviceMapper
import com.oleksandrlysun.forcegateway.domain.boundaries.BluetoothService
import com.oleksandrlysun.forcegateway.domain.models.BluetoothDevice
import io.reactivex.Observable

class BluetoothServiceImpl(context: Context) : BluetoothService {

	private val rxBluetooth = RxBluetooth(context)

	override fun discoverDevices(): Observable<BluetoothDevice> {
		return rxBluetooth.observeDevices()
				.map(BluetoothDeviceMapper::toDomainModel)
	}
}