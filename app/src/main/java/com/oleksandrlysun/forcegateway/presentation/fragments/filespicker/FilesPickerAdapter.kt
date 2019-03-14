package com.oleksandrlysun.forcegateway.presentation.fragments.filespicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrlysun.forcegateway.R
import com.oleksandrlysun.forcegateway.extensions.bindView
import javax.inject.Inject

class FilesPickerAdapter @Inject constructor(private val listener: FilesPickerListener) : RecyclerView.Adapter<FilesPickerAdapter.ViewHolder>() {

	private var items = emptyList<SelectableFileItem>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
		return ViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = items[position]
		val imageIdRes = if (item.file.isFile) R.drawable.ic_file else R.drawable.ic_folder
		val backgroundIdRes = if (item.isSelected) R.color.colorAccent else R.color.colorBackground
		with(holder) {
			itemView.setBackgroundResource(backgroundIdRes)
			fileImageView.setImageResource(imageIdRes)
			fileNameTextView.text = item.file.name
			itemView.setOnClickListener {
				listener.onFileItemClick(item)
			}
		}
	}

	override fun getItemCount(): Int {
		return items.size
	}

	fun setItems(items: List<SelectableFileItem>) {
		this.items = items
		notifyDataSetChanged()
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		val fileImageView by bindView<ImageView>(R.id.image_file)
		val fileNameTextView by bindView<TextView>(R.id.text_file_name)
	}
}