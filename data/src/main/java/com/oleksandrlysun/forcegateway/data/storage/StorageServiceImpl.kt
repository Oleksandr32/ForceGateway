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
	override fun isFolderExists(path: String): Boolean {
		val root = Environment.getExternalStorageDirectory()
		val folder = File(root, path)
		return folder.exists()
	}

	@RequiresPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
	override fun createFolder(path: String) {
		val root = Environment.getExternalStorageDirectory()
		val folder = File(root, path)
		folder.mkdir()
	}

	@RequiresPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
	override fun getFiles(path: String): Single<List<FileModel>> {
		return Single.fromCallable {
			val root = Environment.getExternalStorageDirectory()
			val folder = File(root, path)
			return@fromCallable getFiles(folder).map(FileMapper::toDomainModel)
		}
	}

	private fun getFiles(folder: File): List<File> {
		val files = mutableListOf<File>()
		folder.listFiles()
				.forEach { file ->
					if (file.isDirectory) {
						files.addAll(getFiles(file))
					} else {
						files.add(file)
					}
				}
		return files
	}
}