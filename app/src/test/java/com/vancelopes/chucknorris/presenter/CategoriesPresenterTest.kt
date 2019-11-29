package com.vancelopes.chucknorris.presenter

import com.nhaarman.mockitokotlin2.whenever
import com.vancelopes.chucknorris.api.ChuckAPI
import com.vancelopes.chucknorris.view.CategoriesView
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class CategoriesPresenterTest {
    @Mock
    private lateinit var view: CategoriesView

    private lateinit var p: CategoriesPresenter

    private lateinit var testScheduler: TestScheduler

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        testScheduler = TestScheduler()
        p = CategoriesPresenter(view)
    }

    @After
    fun close() {
        p.onViewDestroyed()
    }

    @Test
    fun loadCategoriesSuccess() {
        val categories: List<String> = ArrayList()
        p.onSuccess(arrayListOf())
        p.loadCategories()
        verify(view, only()).showCategories(categories)
    }

    @Test
    fun loadCategoriesFail() {
        p.onError()
        p.loadCategories()
        verify(view, only()).showError()
    }

    @Test
    fun failingTest() {
        p.loadCategoriesTest(testScheduler)
        testScheduler.triggerActions()
        verify(view).showCategories(arrayListOf("s"))
    }
}