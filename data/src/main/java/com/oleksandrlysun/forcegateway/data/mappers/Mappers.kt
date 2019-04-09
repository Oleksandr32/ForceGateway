package com.oleksandrlysun.forcegateway.data.mappers

import com.oleksandrlysun.forcegateway.domain.models.BluetoothDevice as DomainBluetoothDevice
import android.bluetooth.BluetoothDevice as DataBluetoothDevice
import com.oleksandrlysun.forcegateway.domain.models.FileModel
import com.oleksandrlysun.forcegateway.domain.models.FileType
import java.io.File

object FileMapper : Mapper<File, FileModel>() {

	override fun toDomainModel(value: File): FileModel {
		val fileType = if (value.isFile) FileType.FILE else FileType.FOLDER
		return FileModel(
				path = value.path,
				type = fileType,
				name = value.name,
				sizeInMB = convertBytesToMB(value.length()),
				extension = value.extension,
				subFiles = value.listFiles()?.size ?: 0
		)
	}

	private fun convertBytesToMB(bytes: Long): Double {
		return bytes.toDouble() / (1024 * 1024)
	}
}

object BluetoothDeviceMapper : Mapper<DataBluetoothDevice, DomainBluetoothDevice>() {

	override fun toDomainModel(value: DataBluetoothDevice): DomainBluetoothDevice {
		return DomainBluetoothDevice(value.name, value.address)
	}
}