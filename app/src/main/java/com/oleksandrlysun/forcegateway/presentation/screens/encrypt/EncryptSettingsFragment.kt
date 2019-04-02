package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputEditText
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.databinding.FragmentEncryptSettingsBinding
import com.oleksandrlysun.forcegateway.extensions.bindView
import javax.inject.Inject

class EncryptSettingsFragment : Fragment() {

    @Inject
    lateinit var viewModel: EncryptViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        injectDependencies()
        val binding = FragmentEncryptSettingsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    private fun injectDependencies() {
        (activity as EncryptActivity).component.inject(this)
    }
}