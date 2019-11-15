package com.attflederx.dailydriver.ui.topics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.attflederx.dailydriver.R

class TopicsFragment : Fragment() {

    private lateinit var topicsViewModel: TopicsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        topicsViewModel =
            ViewModelProviders.of(this).get(TopicsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_topics, container, false)

        return root
    }
}