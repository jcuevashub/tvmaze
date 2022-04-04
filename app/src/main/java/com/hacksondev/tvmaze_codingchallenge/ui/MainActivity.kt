package com.hacksondev.tvmaze_codingchallenge.ui

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.hacksondev.tvmaze_codingchallenge.R
import com.hacksondev.tvmaze_codingchallenge.databinding.ActivityMainBinding
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.network.TVMazeService.Companion.getInstance
import com.hacksondev.tvmaze_codingchallenge.repository.ShowRepository
import com.hacksondev.tvmaze_codingchallenge.util.hide
import com.hacksondev.tvmaze_codingchallenge.util.show
import com.hacksondev.tvmaze_codingchallenge.viewmodel.MainViewModel
import com.hacksondev.tvmaze_codingchallenge.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newArrayList: ArrayList<Show>
    private lateinit var viewModelAdapter: MainAdapter
    private lateinit var tempArrayList: ArrayList<Show>
    private val api = getInstance()

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tempArrayList = ArrayList<Show>()
        newArrayList = ArrayList<Show>()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelFactory(ShowRepository(api))).get(MainViewModel::class.java)

        setContentView(binding.root)
         viewModelAdapter = MainAdapter { startView, show ->
            DetailActivity.startActivity(this, startView, show)
        }
        binding.apply {
            vm = viewModel
        }
        binding.recyclerView.adapter = viewModelAdapter

        lifecycleScope.launch {
            viewModel.showsList.observe(this@MainActivity) {
                binding.loadingSpinner.hide()
                binding.errorLayout.hide()
                viewModelAdapter.submitList(it)
                newArrayList.addAll(it)
            }
            viewModel.errorMessage.observe(this@MainActivity){
                binding.loadingSpinner.hide()
                binding.errorLayout.show()
            }
            viewModel.getAllShows()
        }
    }

    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val search = menu?.findItem(R.id.search)
        val searchView = search?.actionView as? SearchView
        searchView?.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
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
            binding.recyclerView.adapter!!.notifyDataSetChanged()
        } else {
            tempArrayList.clear()
            tempArrayList.addAll(newArrayList)
            binding.recyclerView.adapter!!.notifyDataSetChanged()
        }
        viewModelAdapter.submitList(tempArrayList)
        return true
    }
}
