package com.oleksandrlysun.forcegateway.extension

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import com.oleksandrlysun.forcegateway.utils.lazyUnsynchronized

fun <ViewT: View> Activity.bindView(@IdRes idRes: Int): Lazy<ViewT> {
	return lazyUnsynchronized {
		findViewById<ViewT>(idRes)
	}
}