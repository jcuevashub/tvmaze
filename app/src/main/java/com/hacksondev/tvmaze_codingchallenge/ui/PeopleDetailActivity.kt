package com.hacksondev.tvmaze_codingchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.hacksondev.tvmaze_codingchallenge.R
import com.hacksondev.tvmaze_codingchallenge.TVMazeApp
import com.hacksondev.tvmaze_codingchallenge.databinding.ActivityDetailBinding
import com.hacksondev.tvmaze_codingchallenge.databinding.ActivityPeopleDetailBinding
import com.hacksondev.tvmaze_codingchallenge.domain.Cast
import com.hacksondev.tvmaze_codingchallenge.domain.Person
import com.hacksondev.tvmaze_codingchallenge.domain.Show
import com.hacksondev.tvmaze_codingchallenge.viewmodel.MainViewModel
import com.hacksondev.tvmaze_codingchallenge.viewmodel.ViewModelFactory

class PeopleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPeopleDetailBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_detail)

        binding = ActivityPeopleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelFactory((application as TVMazeApp).repository)).get(MainViewModel::class.java)


        val cast = intent.extras?.getParcelable<Cast>("cast")

        binding.apply {
            people = cast!!.person
            activity = this@PeopleDetailActivity
        }



    }
}