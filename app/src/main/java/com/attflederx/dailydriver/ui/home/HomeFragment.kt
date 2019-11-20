package com.attflederx.dailydriver.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.attflederx.dailydriver.R
import com.attflederx.dailydriver.databinding.FragmentHomeBinding
import com.attflederx.dailydriver.ui.adapters.FeedAdapter
import com.attflederx.dailydriver.ui.adapters.NewsListener
import com.attflederx.dailydriver.ui.adapters.WeatherListener

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        // initialize view model
        val viewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        binding.viewModel = viewModel

        // create adapter w/ specific click handlers
        val adapter = FeedAdapter(WeatherListener {
            Toast.makeText(context, "Just stay home", Toast.LENGTH_LONG).show()
        }, NewsListener {
            Toast.makeText(context, "OH PISS", Toast.LENGTH_LONG).show()
        })
        binding.newsList.adapter = adapter

        // observe data changes to update the list adapter
        viewModel.weather.observe(this, Observer {
            it?.let {
                adapter.submitList(it, viewModel.news.value)
            }
        })
        viewModel.news.observe(this, Observer {
            it?.let {
                adapter.submitList(viewModel.weather.value, it)
            }
        })

        // required for LiveData observation
        binding.lifecycleOwner = this

        // set layout manager
        val layoutMgr = LinearLayoutManager(activity)
        binding.newsList.layoutManager = layoutMgr

        return binding.root
    }
}