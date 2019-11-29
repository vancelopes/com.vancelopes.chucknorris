package com.vancelopes.chucknorris.ui.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vancelopes.chucknorris.R
import com.vancelopes.chucknorris.databinding.ItemCategoryBinding

class CategoriesAdapter(
    private val context: Context,
    private var categories: List<String>,
    private val listener: CategoryItemListener): RecyclerView.Adapter<CategoryViewHolder>() {

    fun updateList(categories: List<String>) { this.categories = categories; notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
       val inflater = LayoutInflater.from(context)
        val binding: ItemCategoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_category, parent, false)
        return CategoryViewHolder(binding, listener)
    }

    override fun getItemCount(): Int { return categories.size }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }
}