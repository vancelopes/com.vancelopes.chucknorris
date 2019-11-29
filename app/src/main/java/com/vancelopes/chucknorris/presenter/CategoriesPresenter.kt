package com.vancelopes.chucknorris.presenter

import com.vancelopes.chucknorris.view.CategoriesView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.annotations.TestOnly

class CategoriesPresenter(categoriesView: CategoriesView): BasePresenter<CategoriesView>(categoriesView) {

    override fun onViewCreated() { loadCategories() }
    override fun onViewDestroyed() { disposable?.dispose() }

    /**
     * Load all Chuck facts categories.
     */
    fun loadCategories() {
        disposable = chuckAPI.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { onSuccess(it) },
                onError = { onError() }
            )
    }

    @TestOnly
    fun loadCategoriesTest(scheduler: Scheduler) {
        disposable = chuckAPI.getCategories()
            .subscribeOn(scheduler)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { view.showCategories(it)
                },
                onError = { onError() }
            )
    }

    fun onSuccess(categories: List<String>) { view.showCategories(categories) }

    fun onError() { view.showError() }
}