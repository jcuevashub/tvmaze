package com.hacksondev.tvmaze_codingchallenge.ui

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hacksondev.tvmaze_codingchallenge.databinding.FavoriteListItemBinding
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.util.layoutInflater
import kotlinx.android.synthetic.main.show_item.view.*

class FavoriteAdapter(
    private val startDetailActivity: (View, Show) -> Unit
) : ListAdapter<Show, FavoriteAdapter.MainViewHolder>(DiffCallback) {
    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainViewHolder.from(parent)

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position), startDetailActivity)
    }

    /**
     * ViewHolder for category items. All work is done by data binding.
     */
    class MainViewHolder(private val binding: FavoriteListItemBinding) :
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
                val binding = FavoriteListItemBinding.inflate(
                    parent.context.layoutInflater,
                    parent,
                    false
                )
                return MainViewHolder(binding)
            }
        }
    }


    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Show]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Show>() {
        override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem == newItem
        }
    }
}