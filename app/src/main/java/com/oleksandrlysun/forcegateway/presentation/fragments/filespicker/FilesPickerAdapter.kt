package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.extensions.bindView
import java.io.File

class FilesPickerAdapter : RecyclerView.Adapter<FilesPickerAdapter.ViewHolder>() {

	var items = emptyList<File>()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
		return ViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = items[position]
		val imageIdRes = if (item.isDirectory) R.drawable.ic_folder else R.drawable.ic_file
		with(holder) {
			fileImageView.setImageResource(imageIdRes)
			fileNameTextView.text = item.name
		}
	}

	override fun getItemCount() = items.size

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		val fileImageView by bindView<ImageView>(R.id.image_file)
		val fileNameTextView by bindView<TextView>(R.id.text_file_name)
	}
}