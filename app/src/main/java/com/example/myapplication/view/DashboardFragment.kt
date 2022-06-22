package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.NewsArticleAdapter
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.data.api.ApiHelperImpl
import com.example.myapplication.data.api.RetrofitBuilder
import com.example.myapplication.data.model.NewsArticles
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.utils.Status
import com.example.myapplication.utils.ViewModelFactory
import com.example.myapplication.viewmodel.DashBoardViewModel

class DashboardFragment:BaseFragment() {
    override fun layoutId(): Int = R.layout.fragment_dashboard

    private lateinit var viewModel: DashBoardViewModel
    private lateinit var adapter: NewsArticleAdapter
    private lateinit var binding : FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        binding.newsListRv.layoutManager = LinearLayoutManager(context)
        adapter =
            NewsArticleAdapter(
                arrayListOf()
            )
        binding.newsListRv.addItemDecoration(
            DividerItemDecoration(
                binding.newsListRv.context,
                (binding.newsListRv.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.newsListRv.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            when (it?.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding.newsListRv.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.newsListRv.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<NewsArticles>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(DashBoardViewModel::class.java)
    }

}