package com.vancelopes.chucknorris.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.vancelopes.chucknorris.R
import com.vancelopes.chucknorris.presenter.CategoriesPresenter
import com.vancelopes.chucknorris.ui.BaseFragment
import com.vancelopes.chucknorris.view.CategoriesView
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : BaseFragment<CategoriesPresenter>(), CategoriesView {

    companion object { const val TAG = "categories" }

    private var categoriesAdapter: CategoriesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter().onViewCreated()
        swipe_refresh.setOnRefreshListener {
            presenter().loadCategories()
        }
        toggleRefresh(false)
        swipe_refresh.isRefreshing = true
    }

    override fun showCategories(categories: List<String>) {
        toggleRefresh(true)
        if(categoriesAdapter == null) {
            categoriesAdapter = CategoriesAdapter(context(), categories, activity!! as CategoryItemListener)
            list_categories.layoutManager = LinearLayoutManager(context)
            list_categories.setHasFixedSize(true)
            list_categories.adapter = categoriesAdapter
            return
        }
        categoriesAdapter?.updateList(categories)
    }

    private fun toggleRefresh(toggle: Boolean) {
        swipe_refresh.isEnabled = toggle
        swipe_refresh.isRefreshing = !toggle
    }

    override fun showError() { showDialog(getString(R.string.error)) }

    /// region Init & Destroy
    override fun initPresenter(): CategoriesPresenter { return CategoriesPresenter(this) }
    override fun onDestroy() { super.onDestroy(); presenter().onViewDestroyed() }
    /// endregion
}