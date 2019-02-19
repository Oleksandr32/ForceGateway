package com.oleksandrlysun.forcegateway.data.storage

import android.Manifest
import android.annotation.SuppressLint
import android.os.Environment
import androidx.annotation.RequiresPermission
import com.oleksandrlysun.forcegateway.domain.boundaries.StorageService
import io.reactivex.Single
import java.io.File

@SuppressLint("MissingPermission")
class StorageServiceImpl : StorageService {

    @RequiresPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    override fun getAllFiles(): Single<List<File>> {
        return Single.fromCallable {
            val root = Environment.getExternalStorageDirectory()
            root.listFiles().toList()
        }
    }
}