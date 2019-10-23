package com.example.gojeck.ui

import android.view.View
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gojeck.R
import com.example.gojeck.model.TrendingRepositoryModel
import kotlinx.android.synthetic.main.tr_trending_repository_fragment_view_holder.view.*

class RepositoryItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        repositoryModel: TrendingRepositoryModel,
        clicklistener: TrendingRepositoryRecyclerViewAdapter.Clicklistener
    ) {
        itemView.repositoryNameTextView.text = repositoryModel.name
        itemView.repositoryAuthorNameTextView.text = repositoryModel.author
        itemView.repositoryUrlTextView.text = itemView.context.getString(
            R.string.tr_repository_url,
            repositoryModel.description,
            repositoryModel.url
        )
        itemView.repositoryLanguageTextView.text = repositoryModel.language
        itemView.repositoryStarsCountTextView.text = repositoryModel.stars.toString()
        itemView.repositoryForksCountTextView.text = repositoryModel.forks.toString()

        Glide.with(itemView.context)
            .asDrawable()
            .load(repositoryModel.avatar)
            .apply(RequestOptions().circleCrop())
            .into(itemView.repositoryImageView)

        itemView.repositoryDetailLayout.isGone = !repositoryModel.isExpanded
        itemView.setOnClickListener {
            clicklistener.onItemClick(repositoryModel)
        }

    }
}