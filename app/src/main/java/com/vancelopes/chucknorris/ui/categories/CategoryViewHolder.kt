package com.vancelopes.chucknorris.ui.categories

import androidx.recyclerview.widget.RecyclerView
import com.vancelopes.chucknorris.databinding.ItemCategoryBinding

class CategoryViewHolder(private val binding: ItemCategoryBinding, private val listener: CategoryItemListener):
    RecyclerView.ViewHolder(binding.root) {
    fun bind(category: String) {
        binding.category = category
        binding.executePendingBindings()
        binding.root.setOnClickListener { listener.onCategoryClick(category) }
    }
}