package com.vancelopes.chucknorris.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vancelopes.chucknorris.R
import com.vancelopes.chucknorris.ui.categories.CategoriesFragment
import com.vancelopes.chucknorris.ui.categories.CategoryItemListener
import com.vancelopes.chucknorris.ui.joke.JokeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CategoryItemListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportFragmentManager.addOnBackStackChangedListener {
            if(supportFragmentManager.backStackEntryCount > 1) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                toolbar.setNavigationOnClickListener{onBackPressed()}
            } else {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                toolbar.setNavigationOnClickListener{finish()}
            }
        }
        toolbar.setNavigationOnClickListener{onBackPressed()}
        val fragment = CategoriesFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .addToBackStack(CategoriesFragment.TAG)
            .commit()
    }

    override fun onCategoryClick(category: String) {
        val jokeFragment = JokeFragment(category)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, jokeFragment)
            .addToBackStack(JokeFragment.TAG)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(supportFragmentManager.backStackEntryCount == 0) finish()
    }
}