package com.oleksandrlysun.forcegateway.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentBinder<out ViewT : View>(
		private val fragment: Fragment,
		private val initializer: (Fragment) -> ViewT?
) : ReadOnlyProperty<Fragment, ViewT?>, LifecycleObserver {

	private object EMPTY

	private var mViewValue: Any? = EMPTY

	init {
		fragment.viewLifecycleOwnerLiveData.observe(fragment, Observer {
			it.lifecycle.addObserver(this)
		})
	}

	override fun getValue(thisRef: Fragment, property: KProperty<*>): ViewT? {
		if (mViewValue === EMPTY) {
			mViewValue = initializer(fragment)
		}
		@Suppress("UNCHECKED_CAST")
		return mViewValue as ViewT?
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	fun onViewDestroyed() {
		mViewValue = EMPTY
	}
}