package com.hacksondev.tvmaze_codingchallenge.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.hacksondev.tvmaze_codingchallenge.R
import com.hacksondev.tvmaze_codingchallenge.domain.Episode


class ExpandableListViewAdapter internal constructor(private val context: Context,
                                                     private val seasons: List<String>,
                                                     private val episodes: HashMap<String, List<Episode>>): BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
       return seasons.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.episodes[this.seasons[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return seasons[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Episode {
        return this.episodes[this.seasons[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val seasonTitle = getGroup(groupPosition) as String

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.season_item, null)
        }

        val episode = convertView!!.findViewById<TextView>(R.id.season)
        episode.text = seasonTitle

        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val episode = getChild(groupPosition, childPosition)

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.episodes_item, null)
        }

        val episodeTextView = convertView!!.findViewById<TextView>(R.id.episode)
        episodeTextView.text = episode.name
        episodeTextView.setOnClickListener{

//            EpisodeDetailActivity.startActivity(context, convertView, episode)
            val intent = Intent(context, EpisodeDetailActivity::class.java)
                .putExtra(EpisodeDetailActivity.EPISODE_KEY, episode)

//            val intent = Intent(context, EpisodeDetailActivity::class.java)
//                intent.putExtra("episodeId", episode.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(context,intent,null)

            Toast.makeText(context,episodeTextView.text.toString(), Toast.LENGTH_SHORT).show()
        }

//        episode.setOnClickListener {
//            val intent = Intent(context, EpisodeDetailActivity::class.java)
//            startActivity(intent)
//        }
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
       return true
    }


}