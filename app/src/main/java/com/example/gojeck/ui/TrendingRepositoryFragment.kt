package com.example.gojeck.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gojeck.R
import com.example.gojeck.model.TrendingRepositoryModel
import com.example.gojeck.viewmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.tr_error_layout.view.*
import kotlinx.android.synthetic.main.tr_trending_repository_fragment.*
import kotlinx.android.synthetic.main.tr_trending_repository_fragment.view.*

class TrendingRepositoryFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var trendingRepositoryRecyclerViewAdapter: TrendingRepositoryRecyclerViewAdapter
    private lateinit var repositoryViewModel: RepositoryViewModel
    private var repository: List<TrendingRepositoryModel> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tr_trending_repository_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shimmerViewContainer.startShimmerAnimation();

        repositoryViewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)
        repositoryViewModel.getRepository().observe(this, Observer {
            shimmerViewContainer.stopShimmerAnimation();
            shimmerViewContainer.visibility = View.GONE
            if (it != null) {
                repository = it
            } else {
                showErrorInformation(view)
            }
            setRecyclerViewAdapter(repository)
        })

        view.no_internet_connection_retry_text?.setOnClickListener {
            repositoryViewModel.getRepository().observe(this, Observer {
                shimmerViewContainer.stopShimmerAnimation();
                shimmerViewContainer.visibility = View.GONE
                view.noInternetLayout.visibility = View.GONE
                view.repositoryRecyclerView.visibility = View.VISIBLE
                if (it != null) {
                    repository = it
                } else {
                    showErrorInformation(view)
                }
                setRecyclerViewAdapter(repository)
            })
        }
    }

    private fun setRecyclerViewAdapter(repository: List<TrendingRepositoryModel>) {
        linearLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        trendingRepositoryRecyclerViewAdapter = TrendingRepositoryRecyclerViewAdapter(repository,
            object : TrendingRepositoryRecyclerViewAdapter.Clicklistener {
                override fun onItemClick(item: TrendingRepositoryModel) {
                    item.isExpanded = !item.isExpanded
                    trendingRepositoryRecyclerViewAdapter.notifyDataSetChanged()
                }
            })

        repositoryRecyclerView?.apply {
            layoutManager = linearLayoutManager
            adapter = trendingRepositoryRecyclerViewAdapter
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        }


    }

    private fun showErrorInformation(view: View) {
        view.shimmerViewContainer.visibility = View.GONE
        view.layout.visibility = View.GONE
        view.repositoryRecyclerView.visibility = View.GONE
        view.noInternetLayout.visibility = View.VISIBLE
    }
}
