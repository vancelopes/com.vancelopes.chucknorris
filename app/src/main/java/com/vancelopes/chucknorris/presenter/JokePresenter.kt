package com.vancelopes.chucknorris.presenter

import com.vancelopes.chucknorris.view.JokeView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class JokePresenter(jokeView: JokeView, var category: String): BasePresenter<JokeView>(jokeView) {

    override fun onViewCreated() { loadJoke() }
    override fun onViewDestroyed() { disposable?.dispose() }

    /**
     * Load a random Chuck Norris joke with designated category.
     */
    private fun loadJoke() {
        disposable = chuckAPI.getRandomJoke(category)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe (
                { joke -> view.showJoke(joke) },
                { view.showError() }
            )
    }
}