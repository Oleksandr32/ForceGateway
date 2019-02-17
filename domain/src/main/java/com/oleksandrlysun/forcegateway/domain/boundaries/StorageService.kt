package com.oleksandrlysun.forcegateway.domain.boundaries

import java.io.File

interface StorageService {

    fun getAllFiles(): List<File>
}