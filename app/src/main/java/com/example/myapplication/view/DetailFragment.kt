package com.example.myapplication.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.data.model.NewsArticles
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.utils.DateUtils

class DetailFragment(news: NewsArticles,liked:Boolean) : BaseFragment() {
    override fun layoutId(): Int = R.layout.fragment_detail


    private lateinit var binding: FragmentDetailBinding
    private var news: NewsArticles? = null
    private var liked :Boolean

    init {
        this.news = news
        this.liked = liked
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        setupUI(binding)
        return binding.root
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClickListeners()
    }

    private fun setupUI(binding: FragmentDetailBinding) {
        val imageUrl = news?.imageUrl
        if (!TextUtils.isEmpty(imageUrl)) {
            binding.img.visibility = View.VISIBLE
            context?.let {
                binding.img.let { it1 ->
                    Glide.with(it).load(imageUrl).into(
                        it1
                    )
                }
            }
        } else {
            binding.img.visibility = View.GONE
        }
        binding.let {
            if (!TextUtils.isEmpty(news?.description)) {
                it.desc.visibility = View.VISIBLE
                it.desc.text = news?.description
            } else {
                it.desc.visibility = View.GONE
            }
            if (!TextUtils.isEmpty(news?.author)) {
                it.author.visibility = View.VISIBLE
                it.author.text = news?.author
            } else {
                it.author.visibility = View.GONE
            }
            if (!TextUtils.isEmpty(news?.publishedAt)) {
                it.publishedAt.visibility = View.VISIBLE
                it.publishedAt.text = news?.publishedAt?.let { it1 -> DateUtils.getDateFormat(it1) }
            } else {
                it.publishedAt.visibility = View.GONE
            }
            if (!TextUtils.isEmpty(news?.title)) {
                it.title.visibility = View.VISIBLE
                it.title.text = news?.title
            } else {
                it.title.visibility = View.GONE
            }
            if (!TextUtils.isEmpty(news?.content)) {
                it.content.visibility = View.VISIBLE
                it.content.text = news?.content
            } else {
                it.content.visibility = View.GONE
            }
            it.progressBar.visibility = View.GONE
            if(liked)
            it.likeButton.setImageDrawable(context?.getDrawable(R.drawable.ic_baseline_thumb_up_alt_24))
            else{
                it.likeButton.setImageDrawable(context?.getDrawable(R.drawable.ic_baseline_thumb_up_alt_gray))
            }

        }


    }

    fun setUpClickListeners() {
        binding.backButton.setOnClickListener {
            val transaction = mActivity?.supportFragmentManager?.beginTransaction()
            transaction?.remove(this)
            transaction?.commit()
        }
        binding.likeButton.setOnClickListener {
            if (!liked) {
                binding.likeButton.setImageDrawable(context?.getDrawable(R.drawable.ic_baseline_thumb_up_alt_24))
                liked = true
            } else {
                binding.likeButton.setImageDrawable(context?.getDrawable(R.drawable.ic_baseline_thumb_up_alt_gray))
                liked = false
            }
        }
    }
}