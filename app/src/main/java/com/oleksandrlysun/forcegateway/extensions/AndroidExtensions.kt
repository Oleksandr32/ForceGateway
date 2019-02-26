package com.oleksandrlysun.forcegateway.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.oleksandrlysun.forcegateway.utils.FragmentBinder
import com.oleksandrlysun.forcegateway.utils.lazyUnsynchronized
import kotlin.properties.ReadOnlyProperty

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

fun <ViewT : View> Fragment.bindView(@IdRes idRes: Int): ReadOnlyProperty<Fragment, ViewT> {
	return FragmentBinder(this) { fragment ->
		fragment.view!!.findViewById<ViewT>(idRes)
	}
}

inline fun <reified T> AppCompatActivity.findFragmentByType(): T? {
	return supportFragmentManager.fragments.find { it is T } as T?
}