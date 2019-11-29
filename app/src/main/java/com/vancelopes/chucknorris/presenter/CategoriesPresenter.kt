package com.vancelopes.chucknorris.presenter

import com.vancelopes.chucknorris.view.CategoriesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoriesPresenter(categoriesView: CategoriesView): BasePresenter<CategoriesView>(categoriesView) {

    override fun onViewCreated() { loadCategories() }
    override fun onViewDestroyed() { disposable?.dispose() }

    /**
     * Load all Chuck facts categories.
     */
    private fun loadCategories() {
        disposable = chuckAPI.getCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { categories -> view.showCategories(categories) }
    }
}