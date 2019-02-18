package com.oleksandrlysun.forcegateway.presentation.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.extensions.startActivity
import com.oleksandrlysun.forcegateway.presentation.screens.encrypt.EncryptActivity

class FeaturesOverviewFragment : Fragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_features_overview, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		view.findViewById<CardView>(R.id.btn_encrypt_files).setOnClickListener {
			activity?.startActivity<EncryptActivity>()
		}
	}
}