package com.example.myapplication.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.data.model.NewsArticles
import com.example.myapplication.databinding.ItemNewsLayoutBinding
import com.example.myapplication.view.DetailFragment
import com.example.myapplication.view.LoginFragment

class NewsArticleAdapter(
    private val users: ArrayList<NewsArticles>
) : RecyclerView.Adapter<NewsArticleAdapter.DataViewHolder>() {

    private lateinit var binding: ItemNewsLayoutBinding

    class DataViewHolder(
        val binding: ItemNewsLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsArticles) {
            binding.newsData = news
            if (!TextUtils.isEmpty(news.imageUrl)) {
                binding.img.visibility = View.VISIBLE
                Glide.with(binding.root.context).load(news.imageUrl).into(binding.img)
            } else {
                binding.img.visibility = View.GONE
            }
            if (!TextUtils.isEmpty(news.author)) {
                binding.author.visibility = View.VISIBLE
            } else {
                binding.author.visibility = View.GONE
            }
            if (news.isSelected) {
                binding.likeButton.setImageResource(R.drawable.ic_baseline_thumb_up_alt_24)
            } else {
                binding.likeButton.setImageResource(R.drawable.ic_baseline_thumb_up_alt_gray)
            }
            // binding.likeButton.setImageDrawable(binding.root.context?.getDrawable(R.drawable.ic_baseline_thumb_up_alt_gray))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        binding = ItemNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }


    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
        binding.title.setOnClickListener {
            val dashboardFragment = DetailFragment(
                users[holder.adapterPosition],
                users[holder.adapterPosition].isSelected
            )
            val transaction =
                (binding.root.context as MainActivity).supportFragmentManager.beginTransaction()
            transaction.add(R.id.parent_container, dashboardFragment)
            transaction.addToBackStack("login_fragment")
            transaction.remove(LoginFragment())
            transaction.commit()
        }


        // holder.binding.likeButton.setImageResource(R.drawable.ic_baseline_thumb_up_alt_gray)
        holder.binding.likeButton.setOnClickListener {
            if (users[holder.adapterPosition].isSelected) {
                users[holder.adapterPosition].isSelected = false
                holder.binding.likeButton.setImageResource(R.drawable.ic_baseline_thumb_up_alt_gray)
            } else {
                users[holder.adapterPosition].isSelected = true
                holder.binding.likeButton.setImageResource(R.drawable.ic_baseline_thumb_up_alt_24)
            }
        }

    }

    fun addData(list: List<NewsArticles>) {
        users.addAll(list)
    }

}