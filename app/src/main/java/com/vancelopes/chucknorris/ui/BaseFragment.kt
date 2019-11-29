package com.vancelopes.chucknorris.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vancelopes.chucknorris.presenter.BasePresenter
import com.vancelopes.chucknorris.view.BaseView

/**
 * Base Fragment for the project.
 * @param BP The presenter (inheriting BasePresenter class).
 */
abstract class BaseFragment<BP: BasePresenter<BaseView>>: BaseView, Fragment() {

    private lateinit var p: BP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        p = initPresenter()
    }

    protected abstract fun initPresenter(): BP
    protected fun presenter(): BP { return p }
    override fun context(): Context { return context!! }
}