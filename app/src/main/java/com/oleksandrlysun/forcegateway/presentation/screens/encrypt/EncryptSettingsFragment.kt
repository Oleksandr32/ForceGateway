package com.oleksandrlysun.forcegateway.presentation.screens.encrypt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.textfield.TextInputEditText
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.domain.models.CryptoAlgorithm
import com.oleksandrlysun.forcegateway.extensions.bindView
import com.oleksandrlysun.forcegateway.extensions.setOnTabSelectedListener
import javax.inject.Inject

class EncryptSettingsFragment : Fragment(), EncryptSettingsView {

	@Inject
	lateinit var presenter: EncryptPresenter

	private val algorithmsTabLayout by bindView<TabLayout>(R.id.tab_layout_algorithms)
	private val keySizeSeekBar by bindView<AppCompatSeekBar>(R.id.seek_bar_key_size)
	private val seedEditText by bindView<TextInputEditText>(R.id.edit_text_seed)
	private val generateKeyButton by bindView<FrameLayout>(R.id.btn_generate_key)

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		injectDependencies()
		return inflater.inflate(R.layout.fragment_encrypt_settings, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupViews()
	}

	override fun setGenerateKeyButtonEnabled(enabled: Boolean) {
		generateKeyButton?.isClickable = enabled
	}

	private fun setupViews() {
		CryptoAlgorithm.values().forEach { algorithm ->
			val tab = Tab().apply {
				text = algorithm.name
			}
			algorithmsTabLayout?.addTab(tab)
		}

		algorithmsTabLayout?.setOnTabSelectedListener { tab ->
			presenter.onAlgorithmTabSelect(tab.text.toString())
		}

		generateKeyButton?.setOnClickListener {
			val keySize = keySizeSeekBar?.progress ?: DEFAULT_KEY_SIZE
			val seed = seedEditText?.text?.toString().orEmpty()
			presenter.generateKey(keySize, seed)
		}
	}

	private fun injectDependencies() {
		(activity as EncryptActivity).component.inject(this)
	}

	companion object {

		private const val DEFAULT_KEY_SIZE = 64
	}
}