package com.hacksondev.tvmaze_codingchallenge.ui

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.lifecycleScope
import com.hacksondev.tvmaze_codingchallenge.R
import com.hacksondev.tvmaze_codingchallenge.base.BaseActivity
import com.hacksondev.tvmaze_codingchallenge.databinding.ActivityMainBinding
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.util.Resource
import com.hacksondev.tvmaze_codingchallenge.util.applyExitMaterialTransform
import com.hacksondev.tvmaze_codingchallenge.util.hide
import com.hacksondev.tvmaze_codingchallenge.util.show
import com.hacksondev.tvmaze_codingchallenge.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.getViewModel
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity<MainViewModel>(), SearchView.OnQueryTextListener {

    override val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private lateinit var newArrayList: ArrayList<Show>
    private lateinit var viewModelAdapter: MainAdapter
    private lateinit var tempArrayList: ArrayList<Show>

    override val viewModel: MainViewModel
        get() = getViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        tempArrayList = ArrayList<Show>()
        newArrayList = ArrayList<Show>()

        applyExitMaterialTransform()
        super.onCreate(savedInstanceState)
        binding.apply {
            vm = viewModel
        }

         viewModelAdapter = MainAdapter { startView, show ->
            DetailActivity.startActivity(this, startView, show)
        }
        binding.recyclerView.adapter = viewModelAdapter

        lifecycleScope.launch {
            viewModel.stateFlow.collect { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        binding.loadingSpinner.hide()
                        binding.errorLayout.hide()
                        viewModelAdapter.submitList(resource.data)
                        resource.data?.let { newArrayList.addAll(it) }
                    }
                    Resource.Status.LOADING -> {
                        binding.loadingSpinner.show()
                        binding.errorLayout.hide()
                    }
                    Resource.Status.ERROR -> {
                        binding.loadingSpinner.hide()
                        binding.errorLayout.show()
                        binding.errorMsg.text = resource.message
                    }
                }
            }
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
