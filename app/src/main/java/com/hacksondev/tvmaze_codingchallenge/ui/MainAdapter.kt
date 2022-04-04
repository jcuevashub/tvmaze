package com.hacksondev.tvmaze_codingchallenge.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hacksondev.tvmaze_codingchallenge.databinding.ShowItemBinding
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.util.layoutInflater

class MainAdapter(
    private val startDetailActivity: (View, Show) -> Unit
) : ListAdapter<Show, MainAdapter.MainViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainViewHolder.from(parent)

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position), startDetailActivity)
    }

    class MainViewHolder(private val binding: ShowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(show: Show, startDetailActivity: (View, Show) -> Unit) {
            with(binding) {
                this.show = show
                root.setOnClickListener {
                    startDetailActivity(itemContainer, show)
                }
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val binding = ShowItemBinding.inflate(
                    parent.context.layoutInflater,
                    parent,
                    false
                )
                return MainViewHolder(binding)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Show>() {
        override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem == newItem
        }
    }
}