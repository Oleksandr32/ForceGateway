package com.oleksandrlysun.forcegateway.domain.boundaries

import io.reactivex.Single
import java.io.File

interface StorageService {

    fun getAllFiles(): Single<List<File>>
}