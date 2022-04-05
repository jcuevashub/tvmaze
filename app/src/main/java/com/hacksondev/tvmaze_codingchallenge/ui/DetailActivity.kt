package com.hacksondev.tvmaze_codingchallenge.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.hacksondev.tvmaze_codingchallenge.R
import com.hacksondev.tvmaze_codingchallenge.TVMazeApp
import com.hacksondev.tvmaze_codingchallenge.databinding.ActivityDetailBinding
import com.hacksondev.tvmaze_codingchallenge.domain.Cast
import com.hacksondev.tvmaze_codingchallenge.domain.Episode
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.viewmodel.MainViewModel
import com.hacksondev.tvmaze_codingchallenge.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.show_item.view.*
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var listCastAdapter: CastAdapter

    private lateinit var listViewAdapter: ExpandableListViewAdapter
    private lateinit var seasonList: List<String>
    private lateinit var episodeList: HashMap<String, List<Episode>>
    private lateinit var expandableListView: ExpandableListView
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        seasonList = ArrayList<String>()
        episodeList = HashMap()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelFactory((application as TVMazeApp).repository)).get(MainViewModel::class.java)
        setContentView(binding.root)
        expandableListView = findViewById(R.id.simpleListView)

        val showItem = intent.extras?.getParcelable<Show>(SHOW_KEY)
        binding.apply {
            show = showItem
            activity = this@DetailActivity
        }


        showItem?.let { it ->
            var concatenatedGenders = "Gender: "
            var concatenatedDays = "Days: "

            for (i in it.genres.indices) {
                concatenatedGenders += it.genres[i]
                if (i < it.genres.size - 1) concatenatedGenders += ", "
            }


            for (i in it.schedule.days.indices) {
                concatenatedDays += it.schedule.days[i]
                if (i < it.schedule.days.size - 1) concatenatedDays += ", "
            }


            binding.time.text = "Time: " + it.schedule.time
            binding.days.text = concatenatedDays
            binding.genderList.text = concatenatedGenders

            fetchCast(showItem)

            fetchSeasons(showItem)

        }


    }

    private fun fetchCast(showItem: Show) {
        lifecycleScope.launch {
            viewModel.castList.observe(this@DetailActivity) {
                it
                binding.castListView.adapter = CastAdapter(this@DetailActivity, it)
            }
            viewModel.errorMessage.observe(this@DetailActivity) {
            }
            viewModel.getShowCast(showItem.id.toString())
        }

        binding.castListView.setOnItemClickListener { parent, _, position, _ ->
            var cast = parent.getItemAtPosition(position)

            val intent = Intent(this@DetailActivity, PeopleDetailActivity::class.java)
                .putExtra("cast", cast as Cast)

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ContextCompat.startActivity(this@DetailActivity, intent, null)
        }
    }

    private fun fetchSeasons(showItem: Show) =
        lifecycleScope.launch {
            viewModel.episodes.observe(this@DetailActivity) {
                it

                for (index in 1..getCount(it)) {
                    (seasonList as ArrayList<String>).add("Season $index")
                }

                for (index in seasonList.indices) {
                    episodeList[seasonList[index]] = it.filter { s -> s.season == index + 1 }
                }

                listViewAdapter = ExpandableListViewAdapter(baseContext, seasonList, episodeList)
                binding.simpleListView.setAdapter(listViewAdapter)
            }
            viewModel.errorMessage.observe(this@DetailActivity) {
            }
            viewModel.getAllEpisodeByShows(showItem.id.toString())
        }

    // returns the number of groups
    fun getCount(list: List<Episode>): Int {
        val grouping = list.groupingBy { it.season }.eachCount()
        return grouping.keys.size
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val SHOW_KEY = "showKey"

        fun startActivity(context: Context, startView: View, show: Show) {
            if (context is Activity) {
                val intent = Intent(context, DetailActivity::class.java)
                    .putExtra(SHOW_KEY, show)

                val options = ActivityOptions.makeSceneTransitionAnimation(
                    context,
                    startView, show.name
                )
                context.startActivity(intent, options.toBundle())
            }
        }
    }
}