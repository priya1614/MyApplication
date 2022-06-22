package com.example.myapplication

import android.os.Bundle
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.view.LoginFragment

class MainActivity : BaseActivity() {
    override fun getContentViewId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpFragment()
    }

    private fun setUpFragment() {
        val fragmentHome = LoginFragment()
        pushFragment(
            R.id.parent_container,
            fragmentHome,
            addToBackStack = false,
            replace = true
        )
    }
}