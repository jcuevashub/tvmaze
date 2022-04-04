package com.hacksondev.tvmaze_codingchallenge.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hacksondev.tvmaze_codingchallenge.databinding.ActivityEpisodeDetailBinding
import com.hacksondev.tvmaze_codingchallenge.domain.Episode

class EpisodeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEpisodeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEpisodeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val episodeItem = intent.extras?.getParcelable<Episode>(EPISODE_KEY)

        binding.apply {
            episode = episodeItem
            activity = this@EpisodeDetailActivity
        }
        binding.season.text = "Season: " + episodeItem!!.season.toString()
        binding.number.text = "Episode: " + episodeItem!!.number.toString()
    }

    companion object {
        const val EPISODE_KEY = "showKey"

        fun startActivity(context: Context, startView: View, episode: Episode) {
            val intent = Intent(context, EpisodeDetailActivity::class.java)
                .putExtra(EPISODE_KEY, episode)

//                val options = ActivityOptions.makeSceneTransitionAnimation(
//                    context,
//                    startView, episode.name
//                )
            context.startActivity(intent,null)
        }
    }
}