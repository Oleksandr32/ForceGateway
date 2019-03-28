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
import com.oleksandrlysun.forcegateway.domain.models.CryptoAlgorithm
import com.oleksandrlysun.forcegateway.extensions.bindView
import com.oleksandrlysun.forcegateway.extensions.setOnTabSelectedListener
import javax.inject.Inject

class EncryptSettingsFragment : Fragment() {

    @Inject
    lateinit var viewModel: EncryptViewModel

    private val algorithmsTabLayout by bindView<TabLayout>(R.id.tab_layout_algorithms)
    private val keySizeSeekBar by bindView<AppCompatSeekBar>(R.id.seek_bar_key_size)
    private val seedEditText by bindView<TextInputEditText>(R.id.edit_text_seed)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        injectDependencies()
        val binding = FragmentEncryptSettingsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        CryptoAlgorithm.values().forEach { algorithm ->
            val tab = algorithmsTabLayout?.newTab()
            if (tab != null) {
                tab.text = algorithm.name
                algorithmsTabLayout?.addTab(tab)
            }
        }
        algorithmsTabLayout?.setOnTabSelectedListener { tab ->
            viewModel.onAlgorithmTabSelect(tab.text.toString())
        }
    }

    private fun injectDependencies() {
        (activity as EncryptActivity).component.inject(this)
    }

    companion object {

        private const val DEFAULT_KEY_SIZE = 64
    }
}