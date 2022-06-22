package com.example.myapplication.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.data.model.NewsArticles
import com.example.myapplication.databinding.ItemNewsLayoutBinding
import com.example.myapplication.view.DashboardFragment
import com.example.myapplication.view.DetailFragment
import com.example.myapplication.view.LoginFragment

class NewsArticleAdapter(
    private val users: ArrayList<NewsArticles>
) : RecyclerView.Adapter<NewsArticleAdapter.DataViewHolder>() {

    private lateinit var binding: ItemNewsLayoutBinding

    class DataViewHolder(
        private val binding: ItemNewsLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsArticles) {
            binding.newsData = news
            if (!TextUtils.isEmpty(news.imageUrl)) {
                binding.img.visibility = View.VISIBLE
                Glide.with(binding.root.context).load(news.imageUrl).into(binding.img)
            } else {
                binding.img.visibility = View.GONE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        binding = ItemNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }


    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
        binding.cardView.setOnClickListener {
            val dashboardFragment = DetailFragment(users[holder.adapterPosition])
            val transaction = (binding.root.context as MainActivity).supportFragmentManager.beginTransaction()
            transaction.add(R.id.parent_container, dashboardFragment)
            transaction.addToBackStack("login_fragment")
            transaction.remove(LoginFragment())
            transaction.commit()
        }
    }

    fun addData(list: List<NewsArticles>) {
        users.addAll(list)
    }

}