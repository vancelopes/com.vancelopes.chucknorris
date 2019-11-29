package com.vancelopes.chucknorris.di.components

import com.vancelopes.chucknorris.api.APIModule
import com.vancelopes.chucknorris.di.modules.ContextModule
import com.vancelopes.chucknorris.presenter.CategoriesPresenter
import com.vancelopes.chucknorris.presenter.JokePresenter
import com.vancelopes.chucknorris.view.BaseView
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ContextModule::class), (APIModule::class)])
interface PresenterInjector {

    fun inject(categoriesPresenter: CategoriesPresenter)
    fun inject(jokePresenter: JokePresenter)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun baseView(baseView: BaseView) : Builder
        fun apiModule(apiModule: APIModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
        fun build(): PresenterInjector
    }
}