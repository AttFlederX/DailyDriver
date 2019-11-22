package com.attflederx.dailydriver.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.attflederx.dailydriver.databinding.HomeWeatherBinding
import com.attflederx.dailydriver.databinding.NewsListItemBinding
import com.attflederx.dailydriver.domain.NewsModel
import com.attflederx.dailydriver.domain.WeatherModel
import com.attflederx.dailydriver.ui.models.FeedItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.ClassCastException

private val ITEM_VIEW_WEATHER = 0
private val ITEM_VIEW_NEWS = 1

class FeedAdapter(val weatherClickListener: WeatherListener,
                  val newsClickListener: NewsListener) :
    ListAdapter<FeedItem, RecyclerView.ViewHolder>(FeedDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun submitList(weather: WeatherModel?, newsList: List<NewsModel>?) {
        adapterScope.launch {
            weather?.let {
                newsList?.let {
                    val items =
                        listOf(FeedItem.WeatherItem(weather)) + newsList.map { FeedItem.NewsItem(it) }
                    withContext(Dispatchers.Main) {
                        submitList(items)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_WEATHER -> WeatherViewHolder.from(parent)
            ITEM_VIEW_NEWS -> NewsViewHolder.from(parent)

            else -> throw ClassCastException("Unknown")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeatherViewHolder -> {
                val weather = getItem(position) as FeedItem.WeatherItem
                holder.bind(weather.weather, weatherClickListener)
            }
            is NewsViewHolder -> {
                val newsItem = getItem(position) as FeedItem.NewsItem
                holder.bind(newsItem.news, newsClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is FeedItem.WeatherItem -> ITEM_VIEW_WEATHER
            is FeedItem.NewsItem -> ITEM_VIEW_NEWS
        }
    }


    // View holders

    class WeatherViewHolder private constructor(val binding: HomeWeatherBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WeatherModel, weatherClickListener: WeatherListener) {
            binding.weather = item
            binding.clickListener = weatherClickListener

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): WeatherViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = HomeWeatherBinding.inflate(inflater, parent, false)

                return WeatherViewHolder(binding)
            }
        }
    }

    class NewsViewHolder private constructor(val binding: NewsListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsModel, newsClickListener: NewsListener) {
            binding.newsItem = item
            binding.clickListener = newsClickListener

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = NewsListItemBinding.inflate(inflater, parent, false)

                return NewsViewHolder(binding)
            }
        }
    }
}

class WeatherListener(val clickListener: () -> Unit) {
    fun onClick(item: WeatherModel) = clickListener()
}

class NewsListener(val clickListener: (newsItem: NewsModel) -> Unit) {
    fun onClick(item: NewsModel) = clickListener(item)
}

class FeedDiffCallback : DiffUtil.ItemCallback<FeedItem>() {
    override fun areItemsTheSame(oldItem: FeedItem, newItem: FeedItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: FeedItem, newItem: FeedItem): Boolean {
        return oldItem == newItem
    }

}