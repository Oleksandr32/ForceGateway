package com.oleksandrlysun.forcegateway.extensions

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

fun <T> Single<T>.uiThread(): Single<T> {
    return observeOn(AndroidSchedulers.mainThread())
}