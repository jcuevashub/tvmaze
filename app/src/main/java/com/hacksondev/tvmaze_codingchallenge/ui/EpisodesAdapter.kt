package com.hacksondev.tvmaze_codingchallenge.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hacksondev.tvmaze_codingchallenge.databinding.ShowItemBinding
import com.hacksondev.tvmaze_codingchallenge.domain.Episode
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.util.layoutInflater

class EpisodesAdapter(
    private val startDetailActivity: (View, Episode) -> Unit
) : ListAdapter<Episode, EpisodesAdapter.MainViewHolder>(DiffCallback) {

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
    class MainViewHolder(private val binding: ShowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(episode: Episode, startDetailActivity: (View, Episode) -> Unit) {
            with(binding) {
                //this.episode = episode
                root.setOnClickListener {
                    //startDetailActivity(itemContainer, Episode)
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

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Show]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem == newItem
        }
    }
}