package com.hacksondev.tvmaze_codingchallenge.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EPISODE_KEY = "showKey"
    }
}