package org.yara.movies.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.yara.movies.data.remote.model.response.search.Search
import org.yara.movies.databinding.ItemSerachLayoutBinding
import org.yara.movies.ui.main.SearchService

class SearchResultAdapter(

        private val list: List<Search>,
        private val searchService: SearchService) :
        RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSerachLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.searchModel = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(var binding: ItemSerachLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.container.setOnClickListener { searchService.onMovieItemClicked(list[adapterPosition].imdbID)
            }
        }
    }
}