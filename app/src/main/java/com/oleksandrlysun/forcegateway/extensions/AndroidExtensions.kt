package com.oleksandrlysun.forcegateway.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import com.oleksandrlysun.forcegateway.utils.lazyUnsynchronized

inline fun <reified T : Activity> Context.startActivity(bundle: Bundle? = null, buildIntent: Intent.() -> Unit = {}) {
	val intent = Intent(this, T::class.java)
	intent.buildIntent()
	startActivity(intent, bundle)
}

fun <ViewT : View> Activity.bindView(@IdRes idRes: Int): Lazy<ViewT> {
	return lazyUnsynchronized {
		findViewById<ViewT>(idRes)
	}
}