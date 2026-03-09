package com.myhiking.app.ui.photolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myhiking.app.data.model.DevicePhoto
import com.myhiking.app.databinding.ItemPhotoPreviewBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PhotoListAdapter(
    private val onPhotoClick: (DevicePhoto) -> Unit,
    private val onPhotoLongClick: ((DevicePhoto) -> Unit)? = null
) : ListAdapter<DevicePhoto, PhotoListAdapter.PhotoViewHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoPreviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PhotoViewHolder(
        private val binding: ItemPhotoPreviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: DevicePhoto) {
            Glide.with(binding.ivThumbnail)
                .load(photo.uri)
                .centerCrop()
                .into(binding.ivThumbnail)

            val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
            binding.tvPhotoDate.text = dateFormat.format(Date(photo.dateTaken))

            binding.root.setOnClickListener {
                onPhotoClick(photo)
            }

            binding.root.setOnLongClickListener {
                onPhotoLongClick?.invoke(photo)
                onPhotoLongClick != null
            }
        }
    }

    class PhotoDiffCallback : DiffUtil.ItemCallback<DevicePhoto>() {
        override fun areItemsTheSame(oldItem: DevicePhoto, newItem: DevicePhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DevicePhoto, newItem: DevicePhoto): Boolean {
            return oldItem == newItem
        }
    }
}
