package com.vancelopes.chucknorris.presenter

import com.vancelopes.chucknorris.api.APIModule
import com.vancelopes.chucknorris.api.ChuckAPI
import com.vancelopes.chucknorris.di.components.DaggerPresenterInjector
import com.vancelopes.chucknorris.di.components.PresenterInjector
import com.vancelopes.chucknorris.di.modules.ContextModule
import com.vancelopes.chucknorris.view.BaseView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 *  Base Presenter for the project.
 *  @param BV The view (inherited by the BaseView class).
 *  @param view The View class mentioned above.
 */
abstract class BasePresenter<out BV: BaseView> (protected val view: BV) {

    @Inject
    lateinit var chuckAPI: ChuckAPI
    protected var disposable: Disposable? = null

    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .apiModule(APIModule)
        .build()

    init { inject() }
    private fun inject() {
        when (this) {
            is CategoriesPresenter -> injector.inject(this)
            is JokePresenter -> injector.inject(this)
        }
    }

    open fun onViewCreated() { }
    open fun onViewDestroyed() { }
}