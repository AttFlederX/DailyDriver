package com.attflederx.dailydriver.ui.sources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.attflederx.dailydriver.R

class SourcesFragment : Fragment() {

    private lateinit var sourcesViewModel: SourcesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sourcesViewModel =
            ViewModelProviders.of(this).get(SourcesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sources, container, false)

        return root
    }
}