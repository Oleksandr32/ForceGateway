package com.oleksandrlysun.forcegateway.presentation.bindingadapters

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter

object ConstraintsBindingAdapters {

	@JvmStatic
	@BindingAdapter("app:layout_constraintHorizontal_bias")
	fun setHorizontalBias(viewGroup: ViewGroup, bias: Float) {
		val params = viewGroup.layoutParams as ConstraintLayout.LayoutParams
		params.horizontalBias = bias
		viewGroup.layoutParams = params
	}
}