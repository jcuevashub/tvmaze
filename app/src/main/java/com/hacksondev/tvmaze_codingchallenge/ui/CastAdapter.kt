package com.hacksondev.tvmaze_codingchallenge.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.hacksondev.tvmaze_codingchallenge.R
import com.hacksondev.tvmaze_codingchallenge.domain.Cast

class CastAdapter(private val context: Activity, private val castList: List<Cast>) : ArrayAdapter<Cast>(context,
    R.layout.cast_item, castList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflate : LayoutInflater = LayoutInflater.from(context)
        val view: View = inflate.inflate(R.layout.cast_item, null)

        val imageView : ImageView = view.findViewById(R.id.profile_pic)
        val username: TextView = view.findViewById(R.id.personName)

        username.text = castList[position].person.name

        Glide.with(context).load(castList[position].person.image.original).into(imageView);

        return view
    }
}