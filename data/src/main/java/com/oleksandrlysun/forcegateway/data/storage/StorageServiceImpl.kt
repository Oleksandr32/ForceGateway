package com.oleksandrlysun.forcegateway.data.storage

import android.Manifest
import android.annotation.SuppressLint
import android.os.Environment
import androidx.annotation.RequiresPermission
import com.oleksandrlysun.forcegateway.data.mappers.FileMapper
import com.oleksandrlysun.forcegateway.domain.boundaries.StorageService
import com.oleksandrlysun.forcegateway.domain.models.FileModel
import io.reactivex.Single
import java.io.File

@SuppressLint("MissingPermission")
class StorageServiceImpl : StorageService {

	@RequiresPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
	override fun getFiles(): Single<List<FileModel>> {
		val path = Environment.getExternalStorageDirectory().absolutePath
		return getFiles(path)
	}

	@RequiresPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
	override fun getFiles(path: String): Single<List<FileModel>> {
		return Single.fromCallable {
			return@fromCallable File(path)
					.listFiles()
					.toList()
					.map(FileMapper::toDomainModel)
		}
	}
}