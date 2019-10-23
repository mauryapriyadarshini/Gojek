package com.example.gojeck.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gojeck.R
import com.example.gojeck.model.TrendingRepositoryModel

private const val VIEW_ERROR_TYPE = 0
private const val VIEW_ITEM_TYPE = 1

class TrendingRepositoryRecyclerViewAdapter(
    private var repositoryList: List<TrendingRepositoryModel>,
    private var clicklistener: Clicklistener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_ITEM_TYPE) {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.tr_trending_repository_fragment_view_holder,
                parent,
                false
            )
            RepositoryItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.tr_error_layout,
                parent,
                false
            )
            ErrorViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        if (repositoryList.isEmpty()) {
            return 1
        } else {
            return repositoryList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (repositoryList.isEmpty()) {
            return VIEW_ERROR_TYPE
        } else {
            return VIEW_ITEM_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is RepositoryItemViewHolder) {
            val repositoryModel = repositoryList[position]
            holder.bind(repositoryModel, clicklistener)
        }
    }

    interface Clicklistener {
        fun onItemClick(item: TrendingRepositoryModel)
    }
}