package com.romainbechard.saturdaytest.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.romainbechard.saturdaytest.data.model.Photo
import com.romainbechard.saturdaytest.databinding.ItemHitBinding
import com.squareup.picasso.Picasso

class PhotoListAdapter(private val viewModel: MainViewModel) :
    ListAdapter<Photo, PhotoListAdapter.ViewHolder>(AlbumDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)

        Picasso.get()
            .load(item.imageUrl)
            .into(holder.binding.ivHit)

        holder.itemView.setOnClickListener {
            if (item.isSelected) {
                holder.binding.cardView.setCardBackgroundColor(Color.parseColor("#16000000"))
                item.isSelected = false
            }else {
                holder.binding.cardView.setCardBackgroundColor(Color.BLUE)
                item.isSelected = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemHitBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MainViewModel, item: Photo) {

            binding.viewModel = viewModel
            binding.photo = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHitBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class AlbumDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.hitId == newItem.hitId
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}