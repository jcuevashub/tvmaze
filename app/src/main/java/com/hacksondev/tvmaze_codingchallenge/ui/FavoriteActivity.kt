package com.hacksondev.tvmaze_codingchallenge.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.hacksondev.tvmaze_codingchallenge.R
import com.hacksondev.tvmaze_codingchallenge.TVMazeApp
import com.hacksondev.tvmaze_codingchallenge.databinding.ActivityFavoriteBinding
import com.hacksondev.tvmaze_codingchallenge.databinding.ActivityMainBinding
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.util.SwipeGesture
import com.hacksondev.tvmaze_codingchallenge.util.hide
import com.hacksondev.tvmaze_codingchallenge.util.show
import com.hacksondev.tvmaze_codingchallenge.viewmodel.MainViewModel
import com.hacksondev.tvmaze_codingchallenge.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll
import java.util.*


class FavoriteActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityFavoriteBinding
    lateinit var viewModel: MainViewModel
    private lateinit var viewModelAdapter: FavoriteAdapter
    private lateinit var tempArrayList: ArrayList<Show>
    private lateinit var newArrayList: ArrayList<Show>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tempArrayList = ArrayList<Show>()
        newArrayList = ArrayList<Show>()
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory((application as TVMazeApp).repository)).get(MainViewModel::class.java)
        val showItem = intent.extras?.getParcelable<Show>("favorite")

        viewModelAdapter = FavoriteAdapter { startView, show ->
            DetailActivity.startActivity(this, startView, show)
        }

        binding.favoriteRecyclerView.adapter = viewModelAdapter
        showItem.let {
            if (it != null) {
                viewModel.addFavorite(showItem!!)
            }
            viewModel.getFavorites()
        }


        lifecycleScope.launch {
            viewModel.allShows.observe(this@FavoriteActivity) {
                newArrayList.addAll(it)
                viewModelAdapter.submitList(newArrayList)
                binding.loadingSpinner.hide()
                binding.errorLayout.hide()
            }
            viewModel.errorMessage.observe(this@FavoriteActivity){
                binding.loadingSpinner.hide()
                binding.errorLayout.show()
            }
        }

        val swipeToDelete = object : SwipeGesture(this@FavoriteActivity) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val show = newArrayList[position]
                newArrayList.removeAt(position)
                viewModel.removeFavorites(show)
                binding.favoriteRecyclerView.adapter?.notifyItemRemoved(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(binding.favoriteRecyclerView)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.favorite_menu, menu)

        val search = menu?.findItem(R.id.search)
        val searchView = search?.actionView as? SearchView
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        tempArrayList.clear()
        val searchText = query!!.lowercase(Locale.getDefault())
        if (searchText.isNotEmpty()) {
            newArrayList.forEach{
                if (it.name!!.lowercase(Locale.getDefault()).contains(searchText)) {
                    tempArrayList.add(it)
                }
            }
            binding.favoriteRecyclerView.adapter!!.notifyDataSetChanged()
        } else {
            tempArrayList.clear()
            tempArrayList.addAll(newArrayList)
            binding.favoriteRecyclerView.adapter!!.notifyDataSetChanged()
        }
        viewModelAdapter.submitList(tempArrayList)
        return true
    }

}