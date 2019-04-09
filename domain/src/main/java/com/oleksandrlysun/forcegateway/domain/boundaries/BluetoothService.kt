package com.oleksandrlysun.forcegateway.domain.boundaries

import com.oleksandrlysun.forcegateway.domain.models.BluetoothDevice
import io.reactivex.Observable

interface BluetoothService {

    fun discoverDevices(): Observable<BluetoothDevice>
}